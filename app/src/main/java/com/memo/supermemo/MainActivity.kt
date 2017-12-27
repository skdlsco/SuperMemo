package com.memo.supermemo

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_search.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private var toggle by Delegates.notNull<ActionBarDrawerToggle>()
    private var isSearchEnabled = false
    var memoList = ArrayList<Memo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        setToolbarCustomView()

        addTextMemo.onClick {
            startActivity<AddActivity>()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> {
                isSearchEnabled = true
                resetToolbar()
            }
            R.id.sort -> {
            }
        }
        return true
    }

    private fun initToolbar() {
        toolbar.let {
            setSupportActionBar(it)
            it.setTitleTextColor(Color.WHITE)
        }
        toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0)
                .apply {
                    drawerLayout.addDrawerListener(this)
                    syncState()
                }

    }

    private fun resetToolbar() {
        isSearchEnabled.let {
            supportActionBar?.setDisplayShowCustomEnabled(it)
            supportActionBar?.setDisplayShowTitleEnabled(!it)
            toggle.isDrawerIndicatorEnabled = !it
            if (it)
                toolbar.menu.clear()
            else
                onCreateOptionsMenu(toolbar.menu)
        }
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerOpen(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
            isSearchEnabled -> {
                isSearchEnabled = false
                resetToolbar()
            }
            else -> super.onBackPressed()
        }
    }

    private fun setToolbarCustomView() {
        val customView = LayoutInflater.from(this).inflate(R.layout.toolbar_search, null)

        customView.backBtn.onClick {
            onBackPressed()
        }

        supportActionBar?.setCustomView(customView,
                ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT))
    }
}
