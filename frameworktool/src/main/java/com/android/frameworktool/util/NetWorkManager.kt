package com.android.frameworktool.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by jichaopeng
 * 2020/3/18
 */
class NetWorkManager {

    fun initNetWorkManager(baseUrl: String) {
        /**
         * 创建Retrofit实例时需要通过Retrofit.Builder,并调用baseUrl方法设置URL。
         * Retrofit2的baseUlr 必须以 /（斜线）结束，不然会抛出一个IllegalArgumentException
         */
        var retrofit = Retrofit.Builder()
            .baseUrl(baseUrl) // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .build()
    }

}