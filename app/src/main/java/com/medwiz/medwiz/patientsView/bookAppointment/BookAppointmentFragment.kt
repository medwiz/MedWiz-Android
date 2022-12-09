package com.medwiz.medwiz.patientsView.bookAppointment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.databinding.FragmentBookAppointmentBinding
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.CustomDateEntity
import com.medwiz.medwiz.model.CustomTimeEntity
import com.medwiz.medwiz.model.DoctorResponse
import com.medwiz.medwiz.patientsView.main.PatientMainActivity
import com.medwiz.medwiz.patientsView.patientsUi.bookAppointment.SelectCustomDateAdapter
import com.medwiz.medwiz.patientsView.patientsUi.bookAppointment.SelectCustomTimeAdapter
import com.medwiz.medwiz.patientsView.patientsUi.bookAppointment.SelectedDateListener
import com.medwiz.medwiz.patientsView.patientsUi.bookAppointment.SelectedTimeListener
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import com.medwiz.medwiz.viewmodels.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class BookAppointmentFragment : Fragment(R.layout.fragment_book_appointment), SelectedTimeListener,
    SelectedDateListener {
    private lateinit var binding: FragmentBookAppointmentBinding
    private val consultationViewModel: ConsultationViewModel by viewModels()
    private var selectCustomTimeAdapter: SelectCustomTimeAdapter?=null
    private var selectCustomDateAdapter: SelectCustomDateAdapter?=null
    private var selectedDoctor:DoctorResponse?=null
    private var strPaymentMode:String="Cash"
    private var selectedDateObj:CustomDateEntity?=null
    private var selectedTimeObj:CustomTimeEntity?=null
    private var customDateList=ArrayList<CustomDateEntity>()
    private var customTimeList=ArrayList<CustomTimeEntity>()
    private var isCash:Boolean=false;
    var paymentTypeList = arrayOf("Select Payment Mode","Cash", "Online")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookAppointmentBinding.bind(view)
        selectedDoctor=arguments?.getParcelable<DoctorResponse>(UtilConstants.doctor)!!
        binding.imgBack.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.nameTextView.text=selectedDoctor!!.firstname+" "+selectedDoctor!!.lastname
        binding.tvSpecialization.text=selectedDoctor!!.specialization

        binding.tvAmount.text="Rs. "+selectedDoctor!!.fees.toString()

        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentTypeList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        binding.spinnerPayment.adapter = aa

        binding.spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(position>0){
                    strPaymentMode=paymentTypeList[position]
                }
                if(position==1){
                    isCash=true
                }

            }

        }
        createCustomDatesView()
        createCustomTimesView()

        consultationViewModel.consultation.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is Resource.Loading->{
                    (activity as PatientMainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as PatientMainActivity).hideLoading()
                    if(it.data!!.docId>0){
                          val bundle=Bundle()
                        bundle.putParcelable(UtilConstants.consultation,it.data)
                        bundle.putBoolean(UtilConstants.nearbyDocs,true)
                        findNavController().navigate(R.id.action_bookAppointmentFragment_to_paymentFragment,bundle)
                    }


                }
                is Resource.Error->{
                    (activity as PatientMainActivity).hideLoading()
                }
            }
        })
        binding.btContinue.setOnClickListener{
            if(selectedTimeObj==null||selectedDateObj==null){
                Toast.makeText(requireContext(),"Please select time and date",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
           if(selectedTimeObj!!.time.isEmpty()&&selectedTimeObj!!.amOrPm.isEmpty()){
               Toast.makeText(requireContext(),"Please select time",Toast.LENGTH_SHORT).show()
               return@setOnClickListener
           }
            if(selectedDateObj!!.actualDate.isEmpty()){
                Toast.makeText(requireContext(),"Please select date",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val consult=Consultation()
            consult.addedDate=MedWizUtils.getCurrentDate()
            consult.isActive=true
            consult.consDate=selectedDateObj!!.actualDate
            consult.consTime=selectedTimeObj!!.time+" "+selectedTimeObj!!.amOrPm
            consult.fees=selectedDoctor!!.fees
            consult.docId=selectedDoctor!!.id
            val userDetails=(activity as PatientMainActivity).getUserDetails()
            consult.patientMobile= userDetails.mobile
            consult.patientGender=userDetails.gender
            consult.patientName= userDetails.firstname+" "+userDetails.lastname
            consult.doctorName=selectedDoctor!!.firstname+" "+selectedDoctor!!.lastname
            consult.specialization=selectedDoctor!!.specialization
            consult.age=userDetails.age
            val userId= userDetails.id
            consult.patientId=userId.toLong()
            consult.laboratoryId=0
            consult.pharmaId=0
            consult.filePath=""
            consult.isCash=isCash
            consult.isActive=true
            consult.transactionId=""
            consult.status=""
            getJsonObject(consult)
            val token:String=MedWizUtils.storeValueInPreference(requireContext(),UtilConstants.accessToken,"",false)
            consultationViewModel.createNewConsultation(token, getJsonObject(consult))

        }
    }

    private fun createCustomTimesView() {
        customTimeList=MedWizUtils.getAllTime(8,20)
        selectCustomTimeAdapter = SelectCustomTimeAdapter(requireActivity(),this,customTimeList)
        binding.rcvSelectTime.adapter = selectCustomTimeAdapter
        binding.rcvSelectTime.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

    }

    private fun createCustomDatesView() {
        val sdf = SimpleDateFormat("dd MMM yy")
        for (i in 1..7) {
            val calendar: Calendar = GregorianCalendar()
            calendar.add(Calendar.DATE, i)

            val dayOfWeek= calendar.getTime().toString().substring(0,3)
            val dayOfMonth=calendar[Calendar.DAY_OF_MONTH]
//            val dayN=(calendar as GregorianCalendar).toZonedDateTime().dayOfWeek
//            val dateN=(calendar as GregorianCalendar).toZonedDateTime().dayOfMonth

            val customDate= CustomDateEntity()
            val dayTest = sdf.format(calendar.time)
            customDate.day=dayOfWeek
            customDate.actualDate=dayTest
            customDate.date=dayOfMonth.toString()
            customDate.isSelected=false
            customDateList.add(customDate)
        }

        selectCustomDateAdapter = SelectCustomDateAdapter(requireActivity(),this,customDateList)
        binding.rcvSelectDate.adapter = selectCustomDateAdapter
        binding.rcvSelectDate.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

    }

    override fun onSelectedDate(position:Int,obj: CustomDateEntity) {
        for(item in customDateList){
            item.isSelected = item==obj
        }
        this.selectedDateObj=obj
        this.selectCustomDateAdapter!!.notifyDataSetChanged()//notifyItemChanged(position)
    }

    override fun onSelectedTime(position:Int,obj: CustomTimeEntity) {
        for(item in customTimeList){
            item.isSelected = item==obj
        }
        this.selectedTimeObj=obj
        this.selectCustomTimeAdapter!!.notifyDataSetChanged()//notifyItemChanged(position)

    }

    private fun getJsonObject(consultation: Consultation): JsonObject {
        //total 16 fields
        val requestObj=JsonObject()
        requestObj.addProperty("addedDate",consultation.addedDate)
        requestObj.addProperty("consDate",consultation.consDate)
        requestObj.addProperty("consTime",consultation.consTime)
         requestObj.addProperty("docId",consultation.docId)
        requestObj.addProperty("fees",consultation.fees)
        requestObj.addProperty("filePath",consultation.filePath)
        requestObj.addProperty("isActive",consultation.isActive)
        requestObj.addProperty("isCash",consultation.isCash)
        requestObj.addProperty("laboratoryId",consultation.laboratoryId)
        requestObj.addProperty("pharmaId",consultation.pharmaId)
        requestObj.addProperty("status",UtilConstants.STATUS_UPCOMING)
        requestObj.addProperty("transactionId",consultation.transactionId)
        requestObj.addProperty("patientId",consultation.patientId)
        requestObj.addProperty("patientMobile",consultation.patientMobile)
        requestObj.addProperty("patientGender",consultation.patientGender)
        requestObj.addProperty("patientName",consultation.patientName)
        requestObj.addProperty("doctorName",consultation.doctorName)
        requestObj.addProperty("prescriptionId",0)
        requestObj.addProperty("specialization",consultation.specialization)
        requestObj.addProperty("age",consultation.age)

        return requestObj


    }


}