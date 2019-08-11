package com.qifan.githublister

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.qifan.githublister.network.RepoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repoService: RepoService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoService.getRepositories(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                Log.d("Qifan", "list.size+${list.size}")
            }
    }
}
