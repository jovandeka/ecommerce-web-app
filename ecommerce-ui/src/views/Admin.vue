<template>
    <div class="container p-4">
        <router-link :to="{name: 'Category'}" >
            <button class="btn btn-primary btn-lg"> Admin Category</button>
        </router-link>
        <br />
        <router-link :to="{name: 'AdminProduct'}" >
            <button class="btn btn-primary btn-lg"> Admin Product</button>
        </router-link>
    </div>
</template>
<script>
export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: "Admin",

}
</script>
<style>
    .btn {
        margin-bottom: 20px;
    }
</style>