package com.example.marvel.presentation.utils

const val pub = "636b95b454454878fad55656c78b3ec0"
const val priv = "92020f2538949d10cfb419d544efadb8fa60f9a9"
const val ts = "1"
const val text = ts + priv + pub
const val testmd5 = "9b2ebe44572b4c206a6bb5f379f10729"

val md5 = md5Generator(text)
