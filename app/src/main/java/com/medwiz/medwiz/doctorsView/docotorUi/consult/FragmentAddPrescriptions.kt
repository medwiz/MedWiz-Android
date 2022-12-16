package com.medwiz.medwiz.doctorsView.docotorUi.consult

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.medwiz.medwiz.R
import com.medwiz.medwiz.data.reponse.MedicineResponse
import com.medwiz.medwiz.databinding.AddMedicinePopupBinding
import com.medwiz.medwiz.databinding.FragmentAddPrescriptionBinding
import com.medwiz.medwiz.doctorsView.docotorUi.DoctorsActivity
import com.medwiz.medwiz.model.Consultation
import com.medwiz.medwiz.model.Medication
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentAddPrescriptions : Fragment(R.layout.fragment_add_prescription),
    OnSearchItemListener,PrescriptionListener {
    private lateinit var binding: FragmentAddPrescriptionBinding
    private var medicineAdapter: PrescriptionAdapter? = null
    private var  isMedicine=false
    private var labTestAdapter: LabTestAdapter? = null
    private var medicineList = ArrayList<Medication>()
    private var labTestList = ArrayList<Medication>()
    private var consultation: Consultation? = null
    private var alertBinding: AddMedicinePopupBinding? = null
    private var searchAdapter: SearchAdapter? = null
    private var strSearchBy:String="Brand"
    var searchByList = arrayOf("Search by","Name", "Brand")
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPrescriptionBinding.bind(view)
        medicineList = (activity as PrescriptionMainActivity).getMedicineList()
        consultation = (activity as PrescriptionMainActivity).getConsultation()
        labTestList = (activity as PrescriptionMainActivity).getTestList()
        binding.imgBack.setOnClickListener {
            goBack()
        }
        binding.imgAddPrescription.setOnClickListener {
            hideViews()
            unHidePrescriptionView()
        }

        binding.imgMedicine.setOnClickListener {

            medicineAdapter = null
            showAlertFilter(getString(R.string.add_medicine_title))

        }

        binding.imgTest.setOnClickListener {
            labTestAdapter = null
            showAlertFilter(getString(R.string.add_test_title))

        }
        binding.btAdd.setOnClickListener {
            if (medicineList.size > 0 || labTestList.size > 0) {
                (activity as PrescriptionMainActivity).setPrescriptionData(
                    medicineList,
                    labTestList
                )
                (activity as PrescriptionMainActivity).openSendPrescription()
            }
        }
        if (medicineList.isNotEmpty()) {
            hideViews()
            unHidePrescriptionView()
            createMedicineAdapter()
            medicineAdapter!!.setData(medicineList)
        }
        if (labTestList.isNotEmpty()) {
            createLabtestAdapter()
            labTestAdapter!!.setData(labTestList)
        }



        searchViewModel.medicine.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    (activity as PrescriptionMainActivity).showLoading()
                }

                is Resource.Success -> {
                    (activity as PrescriptionMainActivity).hideLoading()
                    if (it.data!!.size > 0) {
                        this.searchAdapter = SearchAdapter(requireContext(), it.data, this, false,isMedicine,strSearchBy)
                        this.alertBinding!!.rcvSearch.adapter = searchAdapter
                       // this.alertBinding!!.rcvSearch.visibility = View.VISIBLE
                    }

                }

                is Resource.Error -> {
                    (activity as PrescriptionMainActivity).hideLoading()
                }
            }
        })

    }

    private fun createMedicineAdapter() {
        medicineAdapter =
            PrescriptionAdapter(requireContext(), getString(R.string.add_medicine_title),this)
        binding.rcvMedicine.adapter = medicineAdapter
        binding.rcvMedicine.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun createLabtestAdapter() {
        labTestAdapter = LabTestAdapter(requireContext(), getString(R.string.add_test_title))
        binding.rcvTest.adapter = labTestAdapter
        binding.rcvTest.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun unHidePrescriptionView() {
        binding.tvMedicine.visibility = View.VISIBLE
        binding.imgMedicine.visibility = View.VISIBLE
        binding.tvTest.visibility = View.VISIBLE
        binding.imgTest.visibility = View.VISIBLE
    }

    private fun hideViews() {
        binding.imgAddPrescription.visibility = View.GONE
    }

    private fun showAlertFilter(type: String) {
        lateinit var mAlert: AlertDialog
        alertBinding = AddMedicinePopupBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireContext())
        mAlert = builder.setView(alertBinding!!.root)
            .setCancelable(false)
            .create()
        mAlert.show()
        mAlert.setCanceledOnTouchOutside(true)
        alertBinding!!.tvTitle.text = type

        when (type) {
            getString(R.string.add_medicine_title) -> {
                createSearchBySpinner(alertBinding!!)
                this.alertBinding!!.rcvSearch.visibility = View.VISIBLE
                alertBinding!!.tvTypeName.text = getString(R.string.medicine_name)
               // alertBinding!!.rlMedicine.visibility = View.VISIBLE
                isMedicine=true
            }
            getString(R.string.add_test_title) -> {
                alertBinding!!.spinnerSearchBy.visibility=View.GONE
                this.alertBinding!!.rcvSearch.visibility = View.VISIBLE
                alertBinding!!.tvTypeName.text = getString(R.string.lab_test_name)
                //alertBinding!!.rlMedicine.visibility = View.GONE
                alertBinding!!.etDosage.visibility=View.GONE
                isMedicine=false
            }
        }
        alertBinding!!.imgAddMedicine.setOnClickListener {
            when (type) {
                getString(R.string.add_medicine_title) -> {

                    addMedicine(alertBinding!!, mAlert)
                }
                getString(R.string.add_test_title) -> {
                    addLabTest(alertBinding!!, mAlert)
                }
            }


        }

            alertBinding!!.imgCancel.setOnClickListener {
              clearEditText(alertBinding!!)
        }
        alertBinding!!.etMedicineName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(e: Editable?) {
                if (e!!.length > 2&&isMedicine) {
                    searchViewModel.searchMedicine(strSearchBy, e.toString().trim(),true)
                }
                else if(e.length > 2&&!isMedicine) {
                    alertBinding!!.rcvSearch.visibility = View.VISIBLE
                    searchViewModel.searchMedicine(strSearchBy, e.toString().trim(),false)
                }
                if (e.isEmpty() && searchAdapter != null) {
                    searchAdapter!!.searchList.clear()
                    searchAdapter!!.notifyDataSetChanged()
                    alertBinding!!.rcvSearch.visibility = View.VISIBLE
                }
            }

        })

    }

    private fun clearEditText(alertBinding: AddMedicinePopupBinding) {
        if(searchAdapter!=null){
        alertBinding.etMedicineName.setText("")
        alertBinding.etDosage.setText("")
        searchAdapter!!.searchList.clear()
        searchAdapter!!.notifyDataSetChanged()
        }

    }

    private fun createSearchBySpinner(alertBinding: AddMedicinePopupBinding) {
        alertBinding.spinnerSearchBy.visibility=View.VISIBLE
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, searchByList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        alertBinding.spinnerSearchBy.adapter = aa

        alertBinding.spinnerSearchBy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(position>0){
                    clearEditText(alertBinding)
                    strSearchBy=searchByList[position]
                }

            }

        }
    }

    private fun addMedicine(alertBinding: AddMedicinePopupBinding, mAlert: AlertDialog) {
        val medicineName = alertBinding.etMedicineName.text.toString()
        val dosage=alertBinding.etDosage.text.toString()
        if (medicineName.isNotEmpty() && dosage.isNotEmpty()) {
            val medicine = Medication( medicineName ,dosage)
            medicineList.add(medicine)
            createMedicineAdapter()
            createMedicineList(mAlert, medicineList)

        } else {
            MedWizUtils.showErrorPopup(requireContext(), "Please Add values")
        }
    }

    private fun addLabTest(alertBinding: AddMedicinePopupBinding, mAlert: AlertDialog) {
        val labTestName = alertBinding.etMedicineName.text.toString()
        if (labTestName.isNotEmpty()) {
            val labTest = Medication(labTestName,"")
            labTestList.add(labTest)
            createLabtestAdapter()

            createLabList(mAlert, labTestList)

        } else {
            MedWizUtils.showErrorPopup(requireContext(), "Please Add values")
        }
    }


    private fun createMedicineList(mAlert: AlertDialog, list: ArrayList<Medication>) {
        mAlert.dismiss()
        medicineAdapter!!.setData(list)

    }

    private fun createLabList(mAlert: AlertDialog, list: ArrayList<Medication>) {
        mAlert.dismiss()
        labTestAdapter!!.setData(list)

    }

    private fun goBack() {
        startActivity(Intent(requireActivity(), DoctorsActivity::class.java))
        requireActivity().finish()
    }

    override fun onItemClick(obj: MedicineResponse, position: Int) {

       if(!isMedicine){
           alertBinding!!.etMedicineName.setText(obj.name)
       }else{
           when(strSearchBy){
               "Brand"->{ alertBinding!!.etMedicineName.setText(obj.brand)}
               "Name"->{ alertBinding!!.etMedicineName.setText(obj.name)}
           }

           alertBinding!!.etDosage.setText(obj.dosage)
       }

        searchAdapter!!.searchList.clear()
        searchAdapter!!.notifyDataSetChanged()
        alertBinding!!.rcvSearch.visibility = View.GONE
    }

    override fun onClickDosage(obj: Medication, position: Int) {
        MedWizUtils.showErrorPopup(requireContext(),obj.dosage)
    }
}