package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //lateinit หมายถึง ตัวแปรตัวนี้จะมีค่าแน่นอน แต่ตอนนี้ยังไม่มีค่า (null) ก็คือยังไม่ประกาศค่าเริ่มต้น
    private lateinit var binding: ActivityMainBinding

    // life cycle
    // 1.onCreate จะทำงานเมื่อ activity นั้นๆถูกสร้าง
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWidget()

        //        //เวลาเรียก services ของ android ต้องโยน context เข้าไป
//        showToast("Login is success" + "1,200".convertToBath())

    }

    private fun setupWidget() {
        binding.loginButtonLogin.setOnClickListener {
            validate()
        }

        //ปิดการ show scroll ด้านข้าง
        binding.loginScrollview.isVerticalScrollBarEnabled = false
        binding.loginScrollview.isHorizontalScrollBarEnabled = false
    }

    private fun validate() {
        val username = binding.loginEdittextUsername.text.toString()
        val password = binding.loginEdittextPassword.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            showToast("username or password is empty")
            //return object มันออกไป code จะหยุดการทำงานตรงนี้
            return
        }

        //2020 token
        if(username == "art@gmail.com" && password == "1234"){
            val intent = Intent(applicationContext, HomeActivity::class.java);
            startActivity(intent)
            //ไม่ทำให้ย้อนกลับได้ เมื่อกด back
            finish()
            return
        }
        showToast("username or password in valid")

    }
}