<template>
    <div class="card w-100 h-100">
        <div class="embed-responsive embed-responsive-16by9">
            <img :src="category.imageUrl" class="card-img-top embed-responsive-item" alt="Category Image">
        </div>
        <div class="card-body">
            <router-link :to="{name: 'ListProducts', params:{id: category.id}}">
                <h5 class="card-title">{{ category.categoryName }}</h5>
            </router-link>
            <p class="card-text font-italic">{{ category.description.substring(0, 65) }}</p>
            <router-link :to="{ name: 'EditCategory', params: {id: category.id} }" v-show="$route.name == 'Category'">
                <button class="btn btn-primary">Edit</button>
            </router-link>
        </div>
    </div>
</template>

<script>
import { RouterLink } from 'vue-router';

export default{
    name: "CategoryCard",
    props: ["category"],
    methods: {
        listProducts() {
            this.$router.push({
                name: "ListProducts",
                params: { id: this.category.id },
        });
    },
    },
    components: { RouterLink }
}
</script>

<style scoped>
.card-img-top{
    object-fit: cover;
}
</style>