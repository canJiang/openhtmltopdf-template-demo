<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
@font-face {
  font-family: "simkai";
}
* {
  font-family: "simkai";
}
table.datatable {
  border-collapse: collapse;
  border: 1px solid #aaa;
  width: 100%;
}
th, td {
  padding: 2px;
  border: 1px solid #aaa;
}
</style>
</head>
<body>
  <div style="text-align:center;margin: 30px 0;font-size: 30px;">消费记录清单</div>
  <div style="margin: 0 auto;text-align: center;padding: 0px;">
    <table class="datatable">
      <tr>
        <th width="10%">序号</th>
        <th width="20%">消费类型</th>
        <th width="20%">停车场</th>
        <th width="20%">车牌号</th>
        <th width="18%">支付时间</th>
        <th width="12%">支付金额</th>
      </tr>
    <%for (data in dataList) {%>
      <tr>
        <td>${dataLP.index}</td>
        <td>${data.billTypeStr!"未知"}</td>
        <td>${data.parkName!"-"}</td>
        <td>${data.carNo!"-"}</td>
        <td>${data.payTime,dateFormat="yyyy-MM-dd HH:mm:ss"}</td>
        <td>¥${data.billValue,numberFormat="##.##"}</td>
      </tr>
     <%}%>
    </table>
  </div>
</body>
</html>