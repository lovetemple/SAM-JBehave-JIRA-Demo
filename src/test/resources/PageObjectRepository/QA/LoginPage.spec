Page Title: Opentopic dashboard

#Object Definitions
====================================================================================
txt_header      css body.login-page header h1

inp_username    css input#id_username
inp_password    css input#id_password
btn_singin      css button[type="submit"]

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