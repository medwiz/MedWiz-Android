package com.medwiz.medwiz.auth.signUp

/**
 * @Author: Prithwiraj Nath
 * @Date:10/12/22
 */
interface AddMedicineListener {

   fun onClickAddView(obj:AddView,position:Int)
   fun onBrandChange(obj:AddView,position:Int)
   fun onMrpChange(obj:AddView,position:Int)
   fun onItemDelete(obj:AddView,position:Int)
}