package com.myapplication.theguideschool.model

enum class AppEnvironment {

    SANDBOX {
        override fun merchant_Key(): String {
            return "uJkSUaFG"
        }

        override fun merchant_ID(): String {
            return "5693373"
        }

        override fun furl(): String {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php"
        }

        override fun surl(): String {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php"
        }

        override fun salt(): String {
            return "zQakbVGVEi"
        }

        override fun debug(): Boolean {
            return true
        }
    },
    
    PRODUCTION {
        override fun merchant_Key(): String {
            return "uJkSUaFG"
        }

        override fun merchant_ID(): String {
            return "5693373"
        }

        override fun furl(): String {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php"
        }

        override fun surl(): String {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php"
        }

        override fun salt(): String {
            return "zQakbVGVEi"
        }

        override fun debug(): Boolean {
            return false
        }
    };

    abstract fun merchant_Key(): String

    abstract fun merchant_ID(): String

    abstract fun furl(): String

    abstract fun surl(): String

    abstract fun salt(): String

    abstract fun debug(): Boolean


}