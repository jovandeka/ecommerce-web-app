<template>
    <div class="card w-100 h-100" style="margin-top: 10px;">
        <div class="embed-responsive embed-responsive-16by9">
            <img :src="category.imageUrl" class="card-img-top embed-responsive-item" alt="Category Image">
        </div>
        <div class="card-body">
            <h5 class="card-title">{{ category.categoryName }}</h5>
            <p class="card-text">{{ category.description }}</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
    </div>
</template>

<script>
export default{
    name: "CategoryCard",
    props: ["category"],
    methods: {

    }
}
</script>

<style scoped>
.card-img-top{
    object-fit: cover;
}
</style>