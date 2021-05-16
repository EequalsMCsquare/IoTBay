<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=order-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author"
          content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous"></script>

    <title>suppliersInfo</title>
</head>
<body>

<%@ include file="nav.jsp" %>

<fieldset>
    <legend>SEARCH</legend>
    <form action="order?operate=search" method="post">
        <div class="form-row">
            <div class="form-group col-md-4">
                <select id="inputState" class="form-control" name="condition">
                    <option value="id">orderID</option>
                    <option value="date">date</option>
                </select>
                <input class="form-control" name="value"
                       placeholder="please input condition">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

</fieldset>
<a href="customer_profile.jsp">Back to profile</a>
<table class="table" id="table">
    <thead class="thead-dark">
    <tr>
        <th class="text-center" scope="col">NO.</th>
        <th scope="col">CUSTOMERID</th>
        <th scope="col">ORDERITEMSID</th>
        <th scope="col">TOTALPRICE</th>
        <th scope="col">DATE</th>
        <th scope="col">STATUS</th>
        <th scope="col">SHIPMENTID</th>
        <th scope="col">ACTION</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="item" varStatus="idx">
        <tr>
            <th scope="row">${item.id}</th>
            <td>${item.customerId}</td>
            <td>${item.orderItemsId}</td>
            <td>${item.totalPrice}</td>
            <td>${item.date}</td>

            <c:if test="${item.status eq 0 }">
                <td>paid</td>
            </c:if>
            <c:if test="${item.status eq 1 }">
                <td>unpaid</td>
            </c:if>
            <c:if test="${item.status eq 2 }">
                <td>ordered</td>
            </c:if>

            <c:if test="${item.status eq 3 }">
                <td>completed</td>
            </c:if>

            <td>${item.shipmentId}</td>
            <td><input type="button" name="name"
                       onclick="editInfo(${item.id})" class="btn btn-warning"
                       value="EDIT" /> <a href="order?operate=delete&id=${item.id}"
                                          class="btn btn-danger">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="modal fade" id="add" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">ADD</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-group style='margin:0 auto'"
                      action="order?operate=add" method="post">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">id</label>
                        <div class="col-xs-8">
                            <input  name="id" readonly="readonly"
                                    class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">CUSTOMERID</label>
                        <div class="col-xs-8">
                            <input id="customerId" name="customerId" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">ORDERITEMSID</label>
                        <div class="col-xs-8">
                            <input id="orderItemsId" name="orderItemsId"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">TOTALPRICE</label>
                        <div class="col-xs-8">
                            <input id="totalPrice" name="totalPrice" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">DATE</label>
                        <div class="col-xs-8">
                            <input type="date" id="date" name="date" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">STATUS</label>
                        <div class="col-xs-8">
                            <select name="status">
                                <option value="0">ordered</option>
                                <option value="1">pending</option>
                                <option value="2">ordering</option>
                                <option value="3">completed</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">SHIPMENTID</label>
                        <div class="col-xs-8">
                            <input id="shipmentId" name="shipmentId" class="input-text form-control">
                        </div>
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">COLESE</button>
                        <input type="submit" class="btn btn-primary" value="SUBMIT"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">EDIT</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-group style='margin:0 auto'"
                      action="order?operate=edit" method="post">
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">id</label>
                        <div class="col-xs-8">
                            <input id="id" name="id" readonly="readonly"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">CUSTOMERID</label>
                        <div class="col-xs-8">
                            <input id="customerId" name="customerId" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">ORDERITEMSID</label>
                        <div class="col-xs-8">
                            <input id="orderItemsId" name="orderItemsId"
                                   class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">TOTALPRICE</label>
                        <div class="col-xs-8">
                            <input id="totalPrice" name="totalPrice" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">DATE</label>
                        <div class="col-xs-8">
                            <input type="date" id="date" name="date" class="input-text form-control">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">STATUS</label>
                        <div class="col-xs-8">
                            <select name="status">
                                <option value="0">paid</option>
                                <option value="1">unpaid</option>
                                <option value="2">ordered</option>
                                <option value="3">completed</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-xs-4 control-label">SHIPMENTID</label>
                        <div class="col-xs-8">
                            <input id="shipmentId" name="shipmentId" class="input-text form-control">
                        </div>
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">COLESE</button>
                        <input type="submit" class="btn btn-primary" value="SUBMIT"></input>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="input-group col-md-3">
    <br>
    <button class="btn btn-success" onclick="add()"
            style="margin-left: 3px">ADD</button>
    <br>
</div>

<script type="text/javascript">


    function editInfo(obj) {
        var id =obj
        $('#id').val(id);
        console.log(id)
        $('#edit').modal('show');
    }
    function add() {
        $('#add').modal('show');
    }



</script>
</body>
</html>