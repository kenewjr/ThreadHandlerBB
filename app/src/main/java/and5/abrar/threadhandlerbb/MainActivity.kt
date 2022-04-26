package and5.abrar.threadhandlerbb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bb()
    }
    fun bb(){
        val han = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                val pesan2 = msg.obj as String
                tvResult.text = pesan2
            }
        }
        Thread(Runnable {
            btnHitung.setOnClickListener {
            val bb = inBb.text.toString().toInt()
            val tinggi = inTinggi.text.toString().toFloat()
            val bmi = (bb/(tinggi*tinggi))
            var result = ""
            if (bmi<18.5){
                result = "Kurus"
            }else if (bmi>18.5 && bmi<24.9){
                result = "Normal"
            }else if (bmi>25 && bmi<29.9){
                result = "Overweight"
            }else if (bmi>=30){
                result = "Obesitas"
            }

            val pesan = Message.obtain()
            pesan.obj = result
            pesan.target = han
            pesan.sendToTarget()
        }
        }).start()
    }
}