<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibAdmin.jsp" %>
<div class="card w-75 mt-4">
    <div class="card-header bg-primary">
        <h3 class="text-white p-0 m-0">QUẢN LÝ SẢN PHẨM</h3>
    </div>
    <form class="row form-floating p-3" action="/admin/edit-product" method="post" id="saveUserForm"
          enctype="multipart/form-data">
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingNameProduct" placeholder="Name Product" name="name"
                       value="${editProduct.name}">
                <label for="floatingNameProduct">Name Product</label>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" id="floatingSlug" placeholder="slug" name="slug"
                       value="${editProduct.slug}">
                <label for="floatingSlug">Slug</label>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="number" class="form-control" id="floatingQuantity" placeholder="Quantity" name="quantity"
                       value="${editProduct.quantity}">
                <label for="floatingSlug">Quantity</label>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="number" class="form-control" id="floatingUnitPrice" placeholder="Unit Price" name="unitPrice"
                       value="${editProduct.unitPrice}">
                <label for="floatingSlug">Unit Price</label>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="number" class="form-control" id="floatingCategory" placeholder="Category Id" name="categoryId"
                       value="${editProduct.category.id}">
                <label for="floatingSlug">Category Id</label>
            </div>
        </div>
        <div class="col-sm-6 mb-2">
            <div class="form-floating">
                <input type="number" class="form-control" id="floatingDiscount" placeholder="Discount Id" name="discountId"
                       value="${editProduct.discount.id}">
                <label for="floatingSlug">Discount Id</label>
            </div>
        </div>
        <div class="col-sm-12 mb-2">
            <div class="form-floating">
                <textarea class="form-control" placeholder="Description" id="floatingTextarea2" style="height: 100px"></textarea>
                <label for="floatingTextarea2">Description</label>
            </div>
        </div>
        <div class="col-sm-12 mb-2 d-flex">
            <div class="mb-3">
                <img src="/assets/images/product/${editProduct.image}" width="80" height="100"
                     style="border: 2px solid black">
            </div>
            <div class="mb-3 ms-5">
                <label for="formFile" class="form-label">Image</label>
                <input class="form-control" type="file" id="formFile" name="photo" value="${editProduct.image}">
            </div>
        </div>
        <div class="col-sm-12 mb-2 d-flex">
            <div class="mb-3">
                <img src="/assets/images/product/${editProduct.imagePreview1}" width="80" height="100"
                     style="border: 2px solid black">
            </div>
            <div class="mb-3 ms-5">
                <label for="formFile1" class="form-label">Image Preview 1</label>
                <input class="form-control" type="file" id="formFile1" name="photo" value="${editProduct.imagePreview1}">
            </div>
        </div>
        <div class="col-sm-12 mb-2 d-flex">
            <div class="mb-3">
                <img src="/assets/images/product/${editProduct.imagePreview2}" width="80" height="100"
                     style="border: 2px solid black">
            </div>
            <div class="mb-3 ms-5">
                <label for="formFile2" class="form-label">Image Preview 2</label>
                <input class="form-control" type="file" id="formFile2" name="photo" value="${editProduct.imagePreview2}">
            </div>
        </div>
        <div class="col-sm-12 mb-2 d-flex">
            <div class="mb-3">
                <img src="/assets/images/product/${editProduct.imagePreview3}" width="80" height="100"
                     style="border: 2px solid black">
            </div>
            <div class="mb-3 ms-5">
                <label for="formFile3" class="form-label">Image Preview 3</label>
                <input class="form-control" type="file" id="formFile3" name="photo" value="${editProduct.imagePreview3}">
            </div>
        </div>

        <div class="button mt-3 mb-2">
            <button type="submit" class="btn btn-success">Save</button>
            <button type="reset" class="btn btn-warning">Reset</button>
        </div>
    </form>
</div>