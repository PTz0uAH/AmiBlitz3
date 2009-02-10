; A font sensitive Encrypter for data files  ;======================================================================= ;=                            RED Encrypter                            = ;=                                                                     = ;= Demonstration program for the RI STC Library and the RI STC         = ;= Decrunch Library.  Allows decompression of any STC crunched data    = ;= file (memory permitting).                                           = ;=======================================================================  �3.w  ;======================================================================= ;=                         Constant Declarations                       = ;=======================================================================  #WINFLAGS=#WFLG_CLOSEGADGET|#WFLG_ACTIVATE|#WFLG_DRAGBAR|#WFLG_DEPTHGADGET #WINFLAGS=#WINFLAGS | #WFLG_RMBTRAP  #LOADBUTTON       =3 #SAVEBUTTON       =4 #ABOUTBUTTON      =5  #PACKLEN          =0 #UNPACKLEN        =1 #SAFELEN          =2   ;Gadget text position values #_LEFT        =1 #_RIGHT       =2 #_ABOVE       =4 #_BELOW       =8  #_DISABLE     =$40  ;======================================================================= ;=                            Statements                               = ;=======================================================================  ;Centre a line of text about a given x coordinate in a given window � CentreText{win.w,x.w,y.w,t$}   �, � win   strlen.w=�(�9(win),t$,܏(t$))   � x-(strlen/2),y   ݁ t$ � �  � SetNoFile{}   ƛ 0,#SAVEBUTTON   � 0,#SAVEBUTTON   �� 0   �� 1    ƕ 0,#PACKLEN,0   ƕ 0,#UNPACKLEN,0   ƕ 0,#SAFELEN,0 � �  � SetFile{}   ƚ 0,#SAVEBUTTON   � 0,#SAVEBUTTON    ad.l=��(0)    ƕ 0,#PACKLEN,��(0)   ƕ 0,#UNPACKLEN,��(ad)   ƕ 0,#SAFELEN,��(ad) � �  ;======================================================================= ;=                           Initialisation                            = ;======================================================================= �� ��  �2 pa$=192 �2 fi$=192  prog$="STCLoader"  ;***** Get the screen and font to use ***** � 0 � 0 *scr.Screen=�.l(�� �(0)) *myfont.TextAttr=*scr\Font fontheight=*myfont\ta_YSize ad1.l=*myfont\ta_Name fontname$=�$(ad1)  � 1,fontname$,fontheight,0  � OpenGUI  ;======================================================================= ;=                              Main Loop                              = ;=======================================================================  ��   ev.l=�  ; ***** Check to user events *****   � ev     � #IDCMP_GADGETUP       � DoGadgetUp   � �  �� ev=#IDCMP_CLOSEWINDOW  ;***** Close down the program ***** �  ;======================================================================= ;=                              OpenGUI                                = ;=                                                                     = ;= Open the graphical user interface.  This routine uses the OS call   = ;= TextLength_ to calculate the pixel width of a string, it then uses  = ;= these widths to size its gadgets horizontally.  This allows proper  = ;= sizes of gadgets even when using proportional fonts.                = ;======================================================================= .OpenGUI:   butwidth=�(*scr\_RastPort,"About",5)+14   textwidth=�(*scr\_RastPort,"Unpacked length:",16)+14    xoffset=1   yoffset=1    winheight=*scr\BarHeight+5+(3*(fontheight+4))    x=xoffset   y=yoffset    gadx2=x+textwidth+butwidth   gadx3=gadx2+72    Ƃ 0,#LOADBUTTON,x,y,butwidth,fontheight+4,"Load",0   ƈ 0,#PACKLEN+0,gadx2,y,64,fontheight+4,"Unpacked length:",#_LEFT,0    y+fontheight+4   Ƃ 0,#SAVEBUTTON,x,y,butwidth,fontheight+4,"Save",#_DISABLE   ƈ 0,#UNPACKLEN,gadx2,y,64,fontheight+4,"Packed length:",#_LEFT,0    y+fontheight+4   Ƃ 0,#ABOUTBUTTON,x,y,butwidth,fontheight+4,"About",0   ƈ 0,#SAFELEN,gadx2,y,64,fontheight+4,"Safe length:",#_LEFT,0    x=gadx3+obutwidth+8   � 0,0,fontheight+3,x+butwidth+8,winheight,#WINFLAGS,prog$,-1,-1   Ǝ 0,0   �
 1    Ƒ 0,�1+x,�0+1,butwidth,�.-2,0    y=(�./2)-fontheight    CentreText{0,x+butwidth/2,y,"RWE"}   CentreText{0,x+butwidth/2,y+fontheight,"1996"} �  ;======================================================================= ;=                             DoGadgetUp                              = ;=                                                                     = ;= Handle the gadget events from the user.                             = ;======================================================================= .DoGadgetUp:   gad=�    � gad     � #LOADBUTTON       fil$=�("Select load file:",pa$,fi$)       � fil$<>""         SetNoFile{}          � ��(fil$,0)=0           �� prog$,"Could not load the requested file.","  Ok  "         �"           � ��(��(0))=0             �� "Not a stone cracked file!"             �� 0           �"             � ��(1,��(��(0)))=0               �� "Could not allocate destination buffer!"               �� 0             �"               � ��(0),��(1)               SetFile{}             ��           ��         ��       ��      � #ABOUTBUTTON       �� prog$,"A demo program for RISTCLib and RISTCDecrunchLib","  Ok  "      � #SAVEBUTTON       � ��(1)         fil$=�("Select save filename:",pa$,fi$)         � fil$<>""           flag=1           � �(fil$)             �� "Overwrite existing file?","Yes|No"             � reply=0 �! flag=0           ��            � flag=1             � ��(fil$,1)               �� "Saved file okay."             �"               �� "Could not save to the file"             ��           ��         ��       ��    � � �  