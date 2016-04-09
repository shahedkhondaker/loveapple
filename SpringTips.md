# 概要 #




# 内容 #

## コントローラ ##
  * 以下のエラーが起こる。<br />Errors/BindingResult argument declared without preceding model attribute. Check your handler method signature
    * エラーが起こる原因はModelAttributeとBindingResulの順番が誤っているからです。<br />別の言い方でいうと、@ValidのフォームとBindingResultのパラメータの順番が誤っているからです。
    * 解決方法として、@Validのフォーム、BindingResultの順でメソッドパラメータを定義すること。例：[MemberController.java](http://code.google.com/p/loveapple/source/browse/trunk/src/server/src/main/java/cn/loveapple/service/controller/member/action/MemberController.java)