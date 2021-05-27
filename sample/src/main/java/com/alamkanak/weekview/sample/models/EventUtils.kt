package com.alamkanak.weekview.sample.models

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object EventUtils {

    var eventGroupedOnEngineerIDs: Map<String, List<Events>>? = null
    lateinit var arrFiveGroupsArrayKeys: List<List<String>>
    lateinit var keysEngineer: List<String>


    fun getData(): ArrayList<List<Events>>? {

        var arValues: ArrayList<List<Events>>? = null

        /* Convert string data to Class  */
        val listType: Type = object : TypeToken<List<Events?>?>() {}.type
        val arList = Gson().fromJson<List<Events>>(EventData.data, listType)

        val totalEvents = arList.size

        /* Grouping events by engineers id  */
        eventGroupedOnEngineerIDs = arList.groupBy { it.engineer_id }

        /* Set adapter */
        eventGroupedOnEngineerIDs?.let {
            arValues = setGridAdapter(it)
        }

        arrFiveGroupsArrayKeys = DiaryData.splitInto5Keys(eventGroupedOnEngineerIDs!!)

        keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, 0)

        Log.e(" arv alues  ", " $arValues")
        return arValues
    }


    fun setEngineerColumnNames(): Array<String?> {

        var arValues: ArrayList<List<Events>>? = null

        /* Convert string data to Class  */
        val listType: Type = object : TypeToken<List<Events?>?>() {}.type
        val arList = Gson().fromJson<List<Events>>(EventData.data, listType)

        val totalEvents = arList.size

        /* Grouping events by engineers id  */
        eventGroupedOnEngineerIDs = arList.groupBy { it.engineer_id }

        /* Set adapter */
        eventGroupedOnEngineerIDs?.let {
            arValues = setGridAdapter(it)
        }

        arrFiveGroupsArrayKeys = DiaryData.splitInto5Keys(eventGroupedOnEngineerIDs!!)

        EventUtils.keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, 0)


        /* Engineer keys are grouped into five each*/

        val users = UserDetails.getModelFromJson()

        var usersNames = arrayOfNulls<String>(10) // returns Array<String?>

        keysEngineer.forEachIndexed { index, key ->
            val user = users?.first { it.user_id.toString() == key }
            Log.e(" userss ", " ${user?.username}")
            usersNames[index] = user?.username!!

        }

        return usersNames

    }

    fun setGridAdapter(arList: Map<String, List<Events>>): ArrayList<List<Events>> {

        var arList2: Map.Entry<String, List<Events>>
        var nameIndex = 0

        /* This code will fetch 3 engineers name and place it in heading */
        arList.forEach lit@{
            Log.e("name Index ", "setGridAdapter: " + it)

            if(nameIndex == 5) {
                return@lit
            }
            arList2 = it

            Log.e("name Index ", "setGridAdapter: " + it.component2()[0].username)

            when(nameIndex) {
                0 -> {
                   // tv_name_1.text = it.value[0].username
                }

                1 -> {
                   // tv_name_1.text = it.value[0].username
                }

                2 -> {
                    //tv_name_1.text = it.value[0].username
                }

                3 -> {

                }
            }
            nameIndex++
        }

        /* Take list of 5 engineers id from data */

        arrFiveGroupsArrayKeys = DiaryData.splitInto5Keys(arList)
        Log.e("group of keys  ", "setGridAdapter: "+ arrFiveGroupsArrayKeys )

        /* Engineer keys are grouped into five each*/
        keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, 0)

        val eventsData = getFiveEvents(arList, keysEngineer)

        return eventsData

    }


    /* To get no of events depending upon keys */
    /* arList - this is grouped events based on engineer keys */

    private fun getFiveEvents(arList: Map<String, List<Events>>, keysEngineer: List<String>) :ArrayList<List<Events>> {
        var eventsBasedOnEngineers : ArrayList<List<Events>> = ArrayList()

        Log.e("atggg", "getFiveEvents: $keysEngineer   ${keysEngineer.indices}, ")
        for (i in keysEngineer.indices) {
            eventsBasedOnEngineers.add(arList[keysEngineer[i]]!!)
        }

        return eventsBasedOnEngineers
    }
}