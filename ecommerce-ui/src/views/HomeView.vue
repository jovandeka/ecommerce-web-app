<template>
  <div id="home">
    <div id="background-div" class="page-holder bg-cover">
      <div class="container py-5">
        <header class="text-left text-white py-5">
          <h3 class="mb-4" id="heading"><a href="#start-shopping" class="bg-white px-2 py-2 rounded">Start shopping</a></h3>
          <p class="lead mb-0 bg-dark p-1 rounded">Proba text</p>
        </header>
      </div>
    </div>
    <!-- display top categories -->
    <div class="container-fluid" id="topCat">
      <div class="container p-4">
        <div class="row">
          <div class="col-12 text-center">
            <h2 class="pt-3" id="titleCat">Top Categories</h2>
          </div>
        </div>
        <div class="row">
          <div v-for="index in this.categorySize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
            <CategoryCard :category="categories[index-1]"/>
          </div>
        </div>
      </div>
    </div>
    <!-- display top products -->
    
    <div class="container-fluid" id="topProd">
      <div class="container p-4">
        <div class="row">
          <div class="col-12 text-center">
            <h2 class="pt-3" id="titleProd">Top Products</h2>
          </div>
        </div>
        <div class="row">
          <div v-for="index in this.productSize" :key="index" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
            <ProductBox :product="products[index-1]"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryCard from '@/components/Category/CategoryCard.vue';
import ProductBox from '@/components/ProductBox.vue';
export default {
  name: 'HomeView',
  components: {CategoryCard, ProductBox
  },
  props: ["categories", "products"],
  data(){
    return{
      categorySize: 0,
      ProductSize: 0
    }
  },
  mounted(){
    this.categorySize = Math.min(6, this.categories.length);
    this.productSize = Math.min(9, this.products.length);
  }
}
</script>
<style>
  .page-holder{
    min-height: 100vh;
  }
  .bg-cover{
    background-size: cover !important;
  }
  #background-div{
    background: url("../assets/homepage.jpg");
  }
  #heading{
    font-weight: 400;
  }
  #topCat{
    background-color:lemonchiffon;
  }
  #topProd{
    background-color:cornflowerblue;
  }
  #titleCat{
    font-weight: bolder;
  }
  #titleProd{
    font-weight: bolder;
    color: white;
  }
  </style>