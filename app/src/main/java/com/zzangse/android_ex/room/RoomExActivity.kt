package com.zzangse.android_ex.room

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.zzangse.android_ex.R
import com.zzangse.android_ex.databinding.ActivityRoomBinding
import com.zzangse.android_ex.room.data.AppDatabase
import com.zzangse.android_ex.room.data.Product
import com.zzangse.android_ex.room.data.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomExActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLayout()
        setRoomDB()
    }

    private fun setLayout() {
        enableEdgeToEdge()
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.room)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun setRoomDB() {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "product-database").fallbackToDestructiveMigration().build()
        val productDao = db.productDao()
        onClickAddBtn(productDao)
    }

    private fun onClickAddBtn(dao: ProductDao) {
        binding.apply {
            btnAdd.setOnClickListener {
                lifecycleScope.launch {
                    dao.insertAll(
                        Product(1, "니트", 50000, "A"),
                        Product(2, "패딩", 150000, "B"),
                        Product(3, "가디건", 80000, "C"),
                        Product(4, "목도리", 50000, "D"),
                        Product(5, "코트", 300000, "E"),
                    )
                }
            }
            btnUpdate.setOnClickListener {
                lifecycleScope.launch {
                    dao.update(Product(2, "패딩", 170000, "B"))
                }
            }
            btnDelete.setOnClickListener {
                lifecycleScope.launch {
                    dao.delete(Product(2, "패딩", 150000, "B"))
                }
            }
            btnSelectAll.setOnClickListener {
                lifecycleScope.launch {
                    val products = dao.loadAllProducts()
                    Log.d("Products","product: $products")
                }
            }
            binding.btnSelectMinPrice.setOnClickListener {
                lifecycleScope.launch {
                    val products = dao.loadAllProductOverPrice(50000)
                    Log.d("Products","product: $products")
                }
            }
        }
    }
}