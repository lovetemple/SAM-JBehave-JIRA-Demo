Page Title: SAM - Log In

#Object Definitions
====================================================================================

inp_username    css #ctl00__mainConentPlaceholder_tbUserName
inp_password    css #ctl00__mainConentPlaceholder_tbPassword
btn_login      css #ctl00__mainConentPlaceholder_btnLogin

btn_studentView	css	#btnStudentView

lnk_dropbox	xpath	//*[@id="topNav"]/div/ul/li[3]/a
lbl_dropbox     css     label.DropboxLabel
====================================================================================

@all, browsers
--------------------------------
inp_password
    below: inp_username 10 px

@all
--------------------------------
inp_password
    above: inp_username 10 px

@browser
--------------------------------
inp_password
    below: inp_username > 10 px