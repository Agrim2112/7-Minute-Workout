package com.example.a7minuteworkout

class ExerciseModel
    (private var id:Int,
    private var name:String,
    private var image:Int,
    private var desc:String,
    private var isCompleted:Boolean,
    private var isSelected:Boolean,)
{
    fun getid(): Int
    {
        return id
    }
    fun setid(id:Int)
    {
        this.id=id
    }

    fun getname(): String
    {
        return name
    }
    fun setname(name:String)
    {
        this.name=name
    }

    fun getimage(): Int
    {
        return image
    }
    fun setimage(image:Int)
    {
        this.image=image
    }

    fun getdesc(): String
    {
        return desc
    }
    fun setdesc(desc:String)
    {
        this.desc=desc
    }
    fun getisCompleted(): Boolean
    {
        return isCompleted
    }
    fun setisCompleted(isCompleted: Boolean)
    {
        this.isCompleted=isCompleted
    }
    fun getisSelected(): Boolean
    {
        return isSelected
    }
    fun setisSelected(isSelected: Boolean)
    {
        this.isSelected=isSelected
    }
}