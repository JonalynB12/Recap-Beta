package com.proj.recapbeta

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class CourseCatalog : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var courseRecyclerView: RecyclerView
    private lateinit var courseArrayList: ArrayList<Course>
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coursecatalog)

        //actionBar?.setDisplayHomeAsUpEnabled(true)
        //actionBar?.setIcon(R.drawable.ic_baseline_logout_24)


        courseRecyclerView = findViewById(R.id.rview)
        courseRecyclerView.layoutManager = LinearLayoutManager(this)
        courseRecyclerView.setHasFixedSize(true)

        courseArrayList = arrayListOf()

        courseAdapter = CourseAdapter(courseArrayList)
        courseRecyclerView.adapter = courseAdapter

        EventChangeListener()
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Course").orderBy("code", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            courseArrayList.add(dc.document.toObject(Course::class.java))
                        }
                    }
                    courseAdapter.notifyDataSetChanged()
                }

            })

    }
}
/*Firebase Real Time Database
    private fun getCourseData() {
        db = FirebaseFirestore.getInstance().getReference("Course")
        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (courseSnapshot in snapshot.children) {
                        val course = courseSnapshot.getValue(Course::class.java)
                        courseArrayList.add(course!!)
                    }

                    courseRecyclerView.adapter = CourseAdapter(courseArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
*/