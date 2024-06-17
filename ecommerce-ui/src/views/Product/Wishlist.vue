<template>
<div class="container py-4">
    <dvi class="row">
        <div class="col-12 text-center">
            <h4 class="pt-2">My Wishlist</h4>
        </div>
    </dvi>
    <div class="row">
        <div v-for="product of products" :key="product.id" class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
        <ProductBox :product="product"></ProductBox>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios';
import ProductBox from '@/components/ProductBox.vue';
import { store } from '../../StoreToken.js';
export default{
    // eslint-disable-next-line
    name: "Wishlist",
    components: {ProductBox
    },
    data(){
        return{
            token:null,
            products:null,
        }
    },
    props: ["baseURL"],
    methods:{
        fetchWishlist(){
            axios.get(`${this.baseURL}/wishlist/${this.token}`).then((data)=>{
                this.products = data.data;
            })
            .catch((err)=>{
                console.log("err", err);
            });
        }
    },
    mounted(){
        this.token = store.token;
        this.fetchWishlist();
    }
}
</script>