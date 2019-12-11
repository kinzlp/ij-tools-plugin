package com.aptkode.myideademo

import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JPasswordField
import javax.swing.JTextField

/**
 * Created by Keith Liu on 12/5/2019.
 */
class MyIdeaDataDialogWrapper : DialogWrapper(true) {
    private val panel = JPanel(GridBagLayout())
    private val txtMode = JTextField()
    private val txtUsername = JTextField()
    private val txtPassword = JPasswordField()

    init {
        init()
        title = "MyIdea Demo Data"
        val state = MyIdeaDemoPluginSettings.getInstance().state
        txtMode.text = state?.mode

        try {
            val credentialAttributes = CredentialAttributes("MyIdeaPlugin")
            val credentials = PasswordSafe.instance.get(credentialAttributes)
            txtPassword.text = credentials?.getPasswordAsString().toString()
            txtUsername.text = credentials?.userName.toString()
        } catch (e: Exception) {
            print(e)
        }
    }

    override fun createCenterPanel(): JComponent? {
        val gb = GridBag()
            .setDefaultInsets(Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)

        panel.preferredSize = Dimension(400, 200)

        panel.add(label("mode"), gb.nextLine().next().weightx(0.2))
        panel.add(txtMode, gb.next().weightx(0.8))
        panel.add(label("username"), gb.nextLine().next().weightx(0.2))
        panel.add(txtUsername, gb.next().weightx(0.8))
        panel.add(label("password"), gb.nextLine().next().weightx(0.2))
        panel.add(txtPassword, gb.next().weightx(0.8))

        return panel
//        val userField = JTextField()
//        val passwordField = JPasswordField()
//        val panel = panel {
//            noteRow("Login to get notified when the submitted\nexceptions are fixed.")
//            row("Username:") { userField() }
//            row("Password:") { passwordField() }
//            row {
//                checkBox("ok")
//                right {
//                    link("Forgot password?") { /* custom action */ }
//                }
//            }
//            noteRow("""Do not have an account? <a href="https://account.jetbrains.com/login">Sign Up</a>""")
//        }
//        return panel
    }

    override fun doOKAction() {
        println("${txtUsername.text} / ${txtPassword.text} : ${txtMode.text}");
        val mode = txtMode.text
        val username = txtUsername.text
        val password = txtPassword.text
        val state = MyIdeaDemoPluginSettings.getInstance().state
        state?.mode = mode

        val credentialAttributes = CredentialAttributes("MyIdeaPlugin")
        val credentials = Credentials(username, password)
        PasswordSafe.instance.set(credentialAttributes, credentials)
        super.doOKAction()
    }

    private fun label(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }

}