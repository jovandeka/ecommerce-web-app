<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3">Edit product</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-md-6 px-5 px-md-0">
                <form v-if="product">
                    <div class="form-group">
                        <label>Category</label>
                        <select class="form-control" v-model="product.categoryId" required>
                            <option v-for="category of categories" :key="category.id"
                                    :value="category.id">{{ category.categoryName }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" v-model="product.name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" v-model="product.description" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Image Url</label>
                        <input type="text"  v-model="product.imageURL" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" v-model="product.price" class="form-control" required>
                    </div>
                    <button type="button" class="btn btn-primary" @click="editProduct">Submit</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        data(){
            return{
                product: null,
                id: null
            }
        },
        props: ["baseURL", "categories", "products"],
        methods: {
            async editProduct(){
                console.log('product', this.product)
                await axios.post(`${this.baseURL}/product/update/${this.id}`, this.product)
                .then(()=>{
                    // eslint-disable-next-line
                    this.$emit("fetchData");
                    this.$router.push({name:'AdminProduct'});
                    swal({
                        text: "Product has been updated successfully",
                        icon: "success"
                    })
                }).catch(err => console.log('err', err));
            }
        },
        mounted(){
            this.id = this.$route.params.id;
            this.product = this.products.find(product => product.id == this.id);
        }
    }
</script>