<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript">
onload = function() {
  draw();
};
function draw() {
  /* canvas要素のノードオブジェクト */
  var canvas = document.getElementById('canvassample');
  /* canvas要素の存在チェックとCanvas未対応ブラウザの対処 */
  if ( ! canvas || ! canvas.getContext ) {
    return false;
  }
  /* 2Dコンテキスト */
  var ctx = canvas.getContext('2d');
  /* 四角を描く */
  ctx.beginPath();
  ctx.moveTo(20, 20);
  ctx.lineTo(120, 20);
  ctx.lineTo(120, 120);
  ctx.lineTo(20, 120);
  ctx.closePath();
  ctx.stroke();
}

</script>
<canvas id="canvassample" width="140" height="140"></canvas>