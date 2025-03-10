package com.aptkode.myideademo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.util.Consumer

/**
 * Created by Keith Liu on 12/4/2019.
 */
class HelloAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        showCustomDialog()
    }

    private fun showCustomDialog() {
        val myIdeaDataDialogWrapper = MyIdeaDataDialogWrapper()
        if (myIdeaDataDialogWrapper.showAndGet()) {
            myIdeaDataDialogWrapper.close(0)
        }
    }

    private fun getUserName(e: AnActionEvent) {
        val name = Messages.showInputDialog(e.project, "Enter your name", "MyIdeaDemo Data", Messages.getQuestionIcon())
        Messages.showMessageDialog(e.project, "Your name is $name", "My IdeaDemo", Messages.getInformationIcon())
    }

    private fun showFileDialog(e: AnActionEvent) {
        val fileChooserDescriptor = FileChooserDescriptor(false, true, false, false, false, false)
        fileChooserDescriptor.title = "MyIdea demo pick directory"
        fileChooserDescriptor.description = "My file chooser demo"
        FileChooser.chooseFile(fileChooserDescriptor, e.project, null, Consumer {
            Messages.showMessageDialog(e.project, it.path, "Path", Messages.getInformationIcon())
        })
    }
}

class PopupAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        showPopup(e)
    }

    private fun showPopup(e: AnActionEvent) {
        val myIdeaDemoPopupList =
            MyIdeaDemoPopupList("my-idea-list", mutableListOf("Apply", "Orange", "Pineapple", "Guava"))
        if (e.project != null) {
            JBPopupFactory.getInstance().createListPopup(myIdeaDemoPopupList, 5)
                .showCenteredInCurrentWindow(e.project!!)
        }
    }
}