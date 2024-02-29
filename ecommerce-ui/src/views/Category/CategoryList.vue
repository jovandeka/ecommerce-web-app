<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="pt-3">Categories</h3>
            </div>
            <div>{{ categories[0] }}</div>
        </div>
        <div class="row">
            
        </div>
    </div>
</template>

<script>
const axios = require("axios");
//import categoryCard from '../../components/Category/CategoryCard.vue';
export default{
    data(){
        return {
            baseUrl: "https://limitless-lake-55070.herokuapp.com",
            categories: []
        };
    },
    methods: {
        async getCategories(){
            await axios.get(`${this.baseUrl}/category/`)
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

</style>