<template>
    <div class="card ">
        <div>
            <img :src="category.imageUrl" class="card-img-top" alt="...">
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
    name: CategoryCard,
    props: ["category"],
    methods: {

    }
}
</script>