<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<div class="container-xl px-4 mt-4">
    <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-xl-4">
            <!-- Profile picture card-->
            <div class="card mb-4 mb-xl-0">
                <div class="card-header">Profile Picture</div>
                <div class="card-body text-center">
                    <!-- Profile picture image-->
                    <img class="img-account-profile rounded-circle mb-2" src="img/단비.png" alt="" width="300px"
                         height="300px">
                    <!-- Profile picture help block-->
                    <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                    <!-- Profile picture upload button-->
                    <button class="btn btn-primary" type="button">Upload new image</button>
                </div>
            </div>
        </div>
        <div class="col-xl-8">
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Account Details</div>
                <div class="card-body">
                    <form>
                        <!-- Form Group (name)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="userID">ID</label>
                            <input class="form-control" id="userID" type="text" value="userID" readonly>
                        </div>


                        <div class="mb-3">
                            <label class="small mb-1" for="inputName">Name</label>
                            <input class="form-control" id="inputName" type="text" placeholder="Enter your name"
                                   value="name">
                        </div>


                        <!-- Form Group (email address)-->
                        <div class="mb-3">
                            <label class="small mb-1" for="inputEmailAddress">Email address</label>
                            <input class="form-control" id="inputEmailAddress" type="email"
                                   placeholder="Enter your email address" value="name@example.com">
                        </div>

                        <!-- Form Group (phone number)-->
                        <div class="md-3">
                            <label class="small mb-1" for="inputPhone">Phone number</label>
                            <input class="form-control" id="inputPhone" type="tel"
                                   placeholder="Enter your phone number" value="000-000-0000">
                        </div>
                        <br>
                        <div class="row gx-3 mb-3">
                            <div class="col-md-6">
                                <label class="small mb-1" for="inputPhone">Gender</label><br>
                                <div class="form-check form-check-inline" style="margin-right: 50px;">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio1"
                                           value="M" style="width: 25px;height: 25px;border: 1px">
                                    <label class="form-check-label" for="inlineRadio1">남</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                           id="inlineRadio2"
                                           value="F" style="width: 25px;height: 25px;border: 1px">
                                    <label class="form-check-label" for="inlineRadio2">여</label>
                                </div>
                            </div>
                        </div>
                        <br>
                        <!-- Save changes button-->
                        <button class="btn btn-primary" type="button">Save changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>