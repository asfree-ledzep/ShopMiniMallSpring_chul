<%@ page import="com.dto.MemberDTO" %>
<%@ page import="com.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	$(".updateBtn").on("click", function(){
    		var num= $(this).attr("data-xxx");
    		var gAmount=$("#cartAmount"+num).val();
    		var gPrice= $(this).attr("data-price");
    		//console.log(num,":", gAmount, ":", gPrice);
    		var sum= parseInt(gAmount)*parseInt(gPrice);
    		$("#sum"+num).text(sum);
    		console.log($("#sum"+sum).text(sum));
    		$.ajax({
    			url: "cartUpdate",
    			type: "get",
    			dataType:"text",
    			data:{
    				num: num,
    				gAmount: gAmount
    			},
    			success: function(data, status, xhr){
    				var sum= gPrice* gAmount
    				console.log(sum,":");
    				$("#sum"+num).text(sum);
    			},
    			error: function(xhr, status, error){
    				
    			}   			
    		}); //end ajax
    	});// end update
    	
    	$(".delBtn").on("click", function(){
    		var num= $(this).attr("data-xxx");
    		var xxx= $(this)
    		
    		$.ajax({
    			url: "cartDelte",
    			type: "get",
    			dataType:"text",
    			data:{
    				num: num    				
    			},
    			success: function(data, status, xhr){
    				//xxx의 부모 중 "tr" 태그 찾기 후 삭제 
    				
    				xxx.parents().filter("tr").remove();
    			},
    			error: function(xhr, status, error){
    				
    			}   			
    		}); //end ajax
    	});//end function
    	
    	$("#allCheck").on("click", function(){
    		var result= this.checked;
    		console.log("allcheck checked=="+ result);
    		$(".check").each(function(idx, data){
    			this.checked= result;    			
    		});//each end    		
    	});// allCheck end    
    	//전체삭제1
    	$("#delAllCart").on("click", function(){
    		var num=[];
    		$("input:checkbox[name='check']:checked").each(function(idx, data){
    				num[idx]=$(this).val();
    		});
    		location.href="cartDeleteAll?num="+ num;   
    		//$("form").attr("action", "cartDeleteAll");
    		//$("form").submit();
    	});
    	
    	//전체삭제2
    	$("#delAllCart2").on("click", function(){
    		$("form").attr("action", "cartDeleteAll");
    		$("form").submit();
    		
    	});
    	
    	
   });//all end
   
</script>    
<table width="90%" cellspacing="0" cellpadding="0" border="0">

	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>
 
	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
<% //List<CartDTO> list= (List<CartDTO>)session.getAttribute("cartList");
	//System.out.println(list);
%>


	<form name="myForm">  
<c:forEach var="dto" items="${cartList}" varStatus="status"><!--  foreach의 사용 -->

<!--  		 <input type="text" name="num${dto.getNum()}" value="${dto.getNum()}" id="num${dto.getNum()}">
		<input type="text" name="gImage${dto.getNum()}" value="bottom1" id="gImage${dto.getNum()}">
		 <input type="text" name="gName${dto.getNum()}" value="제나 레이스 스커트" id="gName${dto.getNum()}">
		  <input type="text" name="gSize${dto.getNum()}" value="L" id="gSize${dto.getNum()}">
		   <inputsp type="text" name="gColor${dto.getNum()}" value="navy" id="gColor${dto.getNum()}"> 
		   <input type="text" name="gPrice${dto.getNum()}" value="9800" id="gPrice${dto.getNum()}">
-->
		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check${dto.getNum()}" class="check" value="${dto.num}"></td>
			<td class="td_default" width="80">${dto.num}</td>
			<td class="td_default" width="80"><img
				src="images/items/${dto.getgImage()}.gif" border="0" align="center"
				width="80" /></td>
			<td class="td_default" width="300" style='padding-left: 30px'>
			${dto.getgName()}
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(${dto.getgSize()})
					, 색상(${dto.getgColor()})]
			</font></td>
			<td class="td_default" align="center" width="110">
			${dto.getgPrice()}
			</td>
			<td class="td_default" align="center" width="90">
			<input class="input_default" type="text" name="cartAmount"
				id="cartAmount${dto.getNum()}" style="text-align: right" maxlength="3"
				size="2" value="${dto.getgAmount()}"></input></td>
			<td><input type="button" value="수정" 
			class="updateBtn"
			data-xxx="${dto.getNum()}"
			data-price="${dto.getgPrice()}">
			
			</td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum${dto.getNum()}">
				${dto.getgPrice() * dto.getgAmount()}
				</span></td>
			<td><input type="button" value="주문" class="orderBtn" data-xxx="${dto.getNum()}"></td>
			<td class="td_default" align="center" width="30"
				style='padding-left: 10px'>
				<input type="button" value="삭제" id="xx${status.index}"
				 class="delBtn" data-xxx="${dto.getNum()}"></td>
			<td height="10"></td>
		</tr>
</c:forEach>

	</form>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5"><a class="a_black" id="orderAllConfirm"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="a_black" href="#" id="delAllCart"> 전체 삭제하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="#" id="delAllCart2"> 전체 삭제하기2 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="main"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    