package com.example.marvel.presentation.utils

import java.math.BigInteger
import java.security.MessageDigest

 fun md5Generator(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16)
    }
