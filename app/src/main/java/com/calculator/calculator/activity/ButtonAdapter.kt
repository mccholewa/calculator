package com.calculator.calculator.activity


/**
 * Created by mcholewa on 10/08/2017.
 */

//
//class ButtonAdapter(context:Context,keypad: MutableList<Btn>): BaseAdapter() {
//    private var mContext:Context
//    private val gridViewItem: MutableList<Btn>
//    init{
//        this.mContext =context
//        this.gridViewItem = keypad
//    }
//    override fun getCount():Int{
//        return gridViewItem.count()
//    }
//    override fun getItemId(position:Int):Long {
//        return 0
//    }
//    override fun getItem(position: Int):Any?{
//        return null
//    }
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View{
//
//        val view : View
//        val button: Btn = gridViewItem[position]
//        if(convertView == null){
//            val layoutInflater : LayoutInflater = LayoutInflater.from(mContext)
//            view = layoutInflater.inflate(R.layout.keypad,null)
//            val btn: Button = view.findViewById(R.id.btn)
//            btn.text = button.label
//        }else{
//            view = convertView
//        }
//        return view
//    }
//}