<template>
    <div class="container p-4">
        <div class="row">
            <div class="col-12 text-center">
                <h4> Our Products</h4>
                <router-link :to="{name: 'AddProduct'}" id="add-product">
                    <button class="btn btn-info" >
                        Add a new Product
                    </button> </router-link>
            </div>
        </div>
        <div class="row">
            <div v-for="product of products" :key="product.id"
                 class="col-md-6 col-xl-4 col-12 pt-3 d-flex">
                <ProductBox :product="product"/>
            </div>
        </div>
    </div>
</template>
<script>
    import ProductBox from "../../components/ProductBox";
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
        name: "Product",
        components: {ProductBox},
        props:["products"]
    }
</script>

<style>
h4 {
font-family: 'Roboto', sans-serif;
color: #484848;
font-weight: 700;
}

#add-product {
float: right;
font-weight: 500;
}
</style>