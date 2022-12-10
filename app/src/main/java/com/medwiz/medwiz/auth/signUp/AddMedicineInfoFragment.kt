package com.medwiz.medwiz.auth.signUp
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.medwiz.medwiz.R
import com.medwiz.medwiz.auth.viewmodels.AuthViewModel
import com.medwiz.medwiz.databinding.FragmentAddMedicineInfoBinding
import com.medwiz.medwiz.main.MainActivity
import com.medwiz.medwiz.util.MedWizUtils
import com.medwiz.medwiz.util.Resource
import com.medwiz.medwiz.util.UtilConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddMedicineInfoFragment:Fragment(R.layout.fragment_add_medicine_info),AddMedicineListener {
    private var adapter: AddMedicineAdapter?=null
    private val viewModel: AuthViewModel by viewModels()
    private var viewCount:ArrayList<AddView> = ArrayList()
    private var strType=""
    private lateinit var binding: FragmentAddMedicineInfoBinding
    var typeList = arrayOf("Tablet","Drops","Syrup","Inhalers","Injections","others")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddMedicineInfoBinding.bind(view)

        binding.imgBackAddInfo.setOnClickListener{

            findNavController().navigateUp()


        }

        binding.btAdd.setOnClickListener {
            val i=viewCount.size
            if(binding.etMedicineName.text.toString().isNotEmpty()){
            val medArray=JsonArray()
            for (itemObj in viewCount){
                if(itemObj.brandName.isNotEmpty()&&itemObj.mrp>0){
                    val jObj=JsonObject()
                jObj.addProperty("name",itemObj.brandName)
                jObj.addProperty("price",itemObj.mrp)
                    medArray.add(jObj)

                }

            }
            val medicineObj=JsonObject()
            medicineObj.addProperty("medicine_name",binding.etMedicineName.text.toString())
            medicineObj.add("brands",medArray)
            medicineObj.addProperty("bb","kk")
            viewModel.addMedicine(medicineObj)
            }
        }


            createAdapter()






        viewModel.addMedicine.observe(viewLifecycleOwner, Observer {

            when(it){
                is Resource.Loading->{
                    (activity as MainActivity).showLoading()
                }

                is Resource.Success->{
                    (activity as MainActivity).hideLoading()
                    Toast.makeText(requireContext(),"Medicine Added successfully!",Toast.LENGTH_SHORT).show()
                    clearAll()

                }
                is Resource.Error->{
                    (activity as MainActivity).hideLoading()
                    if(it.message==UtilConstants.unauthorized){
                        MedWizUtils.performLogout(requireContext(),requireActivity())
                    }

                }
            }
        })

    }

    private fun createAdapter() {
        adapter = AddMedicineAdapter(requireContext(),this)
        binding.rcvAddMedicine.adapter = adapter
        binding.rcvAddMedicine.layoutManager = LinearLayoutManager(requireContext())
        val addView=AddView(0,"",0.0)
        viewCount.add(addView)
        adapter!!.setData(viewCount,0)
    }

    private fun addViews() {
//        val parent = LinearLayout(requireContext())
//        parent.layoutParams =
//            LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
//        parent.orientation = LinearLayout.HORIZONTAL
//        val etBrandName=EditText(requireContext())
//        val etMrp=EditText(requireContext())
//        parent.addView(etBrandName)
//        parent.addView(etMrp)


    }

    override fun onClickAddView(obj: AddView,position:Int) {
        if(binding.etMedicineName.text.toString().isNotEmpty()){
          for(i in 0 until viewCount.size){
              val viewObj=viewCount[i]
            if(viewObj.brandName.isEmpty()&&viewObj.mrp==0.0){
                viewCount.remove(viewObj)
                val addView=AddView(obj.id,obj.brandName,obj.mrp)
                viewCount.add(i,addView)
            }
        }
//            for (i in 0 until viewCount.size) {
//            }

        //create empty view
        if(viewCount.size<7){
            val newAddView=AddView(position,"",0.0)
            viewCount.add(newAddView)
            adapter!!.setData(viewCount,position)
        }
        }

    }

    private fun clearAll(){
        viewCount.clear()
        createAdapter()
    }

    override fun onBrandChange(obj: AddView, position: Int) {
       val i=0
    }

    override fun onMrpChange(obj: AddView, position: Int) {
        val i=0
    }

    override fun onItemDelete(obj: AddView, position: Int) {
        if(viewCount.size>1){
//         for(objDelete in viewCount){
//             if(objDelete.brandName==obj.brandName&&objDelete.mrp==obj.mrp){
//                 viewCount.remove(objDelete)
//                 adapter!!.setRemoveData(viewCount)
//             }
//         }
            viewCount.removeAt(position)
            adapter!!.setRemoveData(viewCount)
        val i=0
        }
    }


}