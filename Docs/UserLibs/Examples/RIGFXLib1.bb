;Demonstration Of Using The Colour Commands ; ;GFX library (C)1996 Red When Exited ;Example program ;By Stephen McNamara ;Please feel free to use any part of the program ; in whatever way you feel like  ;Open a screen � 0,0,0,320,200,5,0,"GFX-Library Example Program",2,1  ;Set up our palette with some dummy colours � h.l=0 � 15   �
 0,h,h,h,h   �
 0,h+16,h,h,0 � h  ;But keep our screens default colours � h=0 � 3   �
 0,h,�(h),�(h),�(h) � h  ;Get a bitmap to use from the screen � 0,0 �, � 0 � 0  ;And draw some rubbish y=12 � h=1 � 31   �� 0,y,319,y+4,h   y+5 � h  ;Use our palette on the screen �, �	 0  ;And make our library use the palette �� 0  � 0,23 : ݂ "Values    :" � 0,24 : ݂ "AGA values:"  ;Loop until you press the mouse button ��   �7    ;get our mouse coordinates   xm.w=�   ym.w=�    ;get the colour under the mouse pointer   cl.w=�(xm,ym)    ;Print our colour values   � 12,23   ݁ "R:"+݃(��(cl))+" G:"+݃(��(cl))+" B:"+݃(��(cl))+"         "   � 12,24   ݁ "R:"+݃(��(cl))+" G:"+݃(��(cl))+" B:"+݃(��(cl))+"        " �� �(0)  ;Do a nice palette fade using palettes 0 and 1  ;Set up palette 1 to have 32 colours �
 1,31,0,0,0  ;DO the fade! � ratio.q=1 � 0 � -0.01   �� 1,ratio   �, �	 1   �7 � ratio  �7 25 �  