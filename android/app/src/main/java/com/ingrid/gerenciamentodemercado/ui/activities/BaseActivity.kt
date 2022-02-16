package com.ingrid.gerenciamentodemercado.ui.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ingrid.gerenciamentodemercado.R

open class BaseActivity : AppCompatActivity() {

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }
}