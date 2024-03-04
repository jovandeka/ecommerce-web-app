<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Categories</h3>
                <router-link id="add-category" :to="{ name : 'AddCategory'}">
                    <button type="button" class="btn btn-info">Add a new Category</button>
                </router-link>
            </div>
        </div>
        <div class="row">
            <div v-for="category of categories" :key="category.id" class="col-md-6 col-xl-4 col-12 pt-3  justify-content-around d-flex">
                <CategoryCard :category="category" />
            </div>
        </div>
    </div>
</template>

<script>
const axios = require("axios");
import CategoryCard from '../../components/Category/CategoryCard.vue';
export default{
    name: "CategoryList",
    components: {CategoryCard},
    data(){
        return {
            baseUrl: "http://localhost:8080",
            categories: []
        };
    },
    methods: {
        async getCategories(){
            await axios.get(`${this.baseUrl}/category/list`)
            .then(res => this.categories = res.data)
            .catch(err => console.log(err))
        }
    },
    mounted(){
        this.getCategories();
    }
};
</script>

<style scoped>
h4 {
  font-family: 'Roboto', sans-serif;
  color: #484848;
  font-weight: 700;
}

#add-category {
  float: right;
  font-weight: 500;
}
</style>