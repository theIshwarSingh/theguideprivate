package com.myapplication.theguideschool.model.Payumoney

/**
 * Created by Rahul Hooda on 14/7/17.
 */

/**
 * This class keeps all the app prefernces
 */
class AppPreference {

    var dummyAmount = "10"//"10";
    var dummyEmail = "xyz@gmail.com"//"";//d.basak.db@gmail.com
    var productInfo = "product_info"// "product_info";
    var firstName = "firstname" //"firstname";
    var isOverrideResultScreen = true

    internal var isDisableWallet: Boolean = false
    internal var isDisableSavedCards: Boolean = true
    internal var isDisableNetBanking: Boolean = false
    internal var isDisableThirdPartyWallets: Boolean = false
    internal var isDisableExitConfirmation: Boolean = false

    companion object {

        val USER_EMAIL = "user_email"
        val USER_MOBILE = "user_mobile"
        val PHONE_PATTERN = "^[987]\\d{9}$"
        val MENU_DELAY: Long = 300
        var USER_DETAILS = "user_details"
        var selectedTheme = -1
    }
}
