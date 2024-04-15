<template>
    <div class="container p-4">
      <div class="row">
        <div class="col-12 text-center">
          <h4>{{category.categoryName}}</h4>
          <h5>{{msg}}</h5>
        </div>
      </div>
  
      <div class="row">
        <!--<img v-show="len == 0" class="img-fluid" src="../../assets/sorry.jpg" alt="Sorry">-->
        <div v-for="product in product" :key="product.id" class="col-md-6 col-xl-4 col-12 pt-3  justify-content-around d-flex">
          <ProductBox :product="product">
          </ProductBox>
        </div>
      </div>
  </div>
</template>
  
  <script>
  import ProductBox from '@/components/ProductBox.vue';
  export default {
    name: 'ListProducts',
    components: {ProductBox},
    props: ["categories", "products"],
    data() {
        return {
            id: null,
            category: {},
            msg: '',
            product: {}, 
        }
    },
    mounted() {
        this.id = this.$route.params.id;
        this.category = this.categories.find(category => category.id == this.id)
        this.product = this.products.filter(product => product.categoryId == this.id)

        if (this.product.length == 0) {
            this.msg = "no products found"
        } else if (this.product.length == 1) {
            this.msg = "Only 1 product found"
        } else {
            this.msg = this.product.length + " products found"
        }

    }
  }
  </script>
  
  <style scoped>
  h4 {
    font-family: 'Roboto', sans-serif;
    color: #484848;
    font-weight: 700;
  }
  
  h5 {
    font-family: 'Roboto', sans-serif;
    color: #686868;
    font-weight: 300;
  }
  </style>