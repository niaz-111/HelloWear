����   3 l
  <	 = >
 ? @
 A B
  C
  D
 A E
 ? F
  G
 H I
 H J K
  L
  M O P <init> ()V Code LineNumberTable LocalVariableTable this Lollipop InnerClasses ?Landroid/support/v7/widget/helper/ItemTouchUIUtilImpl$Lollipop; onDraw [(Landroid/graphics/Canvas;Landroid/support/v7/widget/RecyclerView;Landroid/view/View;FFIZ)V newElevation F originalElevation Ljava/lang/Object; c Landroid/graphics/Canvas; recyclerView (Landroid/support/v7/widget/RecyclerView; view Landroid/view/View; dX dY actionState I isCurrentlyActive Z StackMapTable findMaxElevation >(Landroid/support/v7/widget/RecyclerView;Landroid/view/View;)F child 	elevation i itemView 
childCount max R 	clearView (Landroid/view/View;)V tag S 
SourceFile ItemTouchUIUtilImpl.java   U W ) R X Y Z [ \ ] ^ - . _ ` a b   c d e f g java/lang/Float h i 6 7 j =android/support/v7/widget/helper/ItemTouchUIUtilImpl$Lollipop >android/support/v7/widget/helper/ItemTouchUIUtilImpl$Honeycomb 	Honeycomb android/view/View java/lang/Object k $android/support/v7/recyclerview/R$id id $item_touch_helper_previous_elevation getTag (I)Ljava/lang/Object; "android/support/v4/view/ViewCompat getElevation (Landroid/view/View;)F valueOf (F)Ljava/lang/Float; setElevation (Landroid/view/View;F)V setTag (ILjava/lang/Object;)V &android/support/v7/widget/RecyclerView getChildCount ()I 
getChildAt (I)Landroid/view/View; 
floatValue ()F 4android/support/v7/widget/helper/ItemTouchUIUtilImpl !android/support/v7/recyclerview/R                  /     *� �                               �  
   E� 3-� � :� %-� � :*,-� b8	-	� -� � *+,-� 	�       & 	   #  $  %  &  ' & ( , ) 5 , D -    f 
 &    	  '      E       E   !    E " #    E $ %    E &     E '     E ( )    E * +  ,    5  - .     �     >+� 
>86� -+� :,� � � 8�� 8�����       .    0  1  2  3  4  5 " 7 ) 8 1 9 5 2 ; <    R    / %  )  0    0 1 )    >       > " #    > 2 %   9 3 )   6 4   ,    � �  5� �   6 7     �     ,+� � M,� ,� � +,� � � +� � *+�