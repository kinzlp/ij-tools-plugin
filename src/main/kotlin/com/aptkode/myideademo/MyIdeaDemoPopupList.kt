package com.aptkode.myideademo

import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep

/**
 * Created by Keith Liu on 12/7/2019.
 */
class MyIdeaDemoPopupList(title: String, fruits: List<String> ) : BaseListPopupStep<String>(title, fruits) {
    override fun isSpeedSearchEnabled(): Boolean {
        return true
    }

    override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*> {
        println("")
        return PopupStep.FINAL_CHOICE
    }
}