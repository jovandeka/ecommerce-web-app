import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AddCategory from '../views/Category/AddCategory.vue'
import Category from '../views/Category/Category.vue'
import Product from '../views/Product/Product.vue'
import Admin from "../views/Admin.vue";
import AddProduct from "../views/Product/AddProduct.vue";
import EditCategory from "../views/Category/EditCategory.vue";
import EditProduct from "../views/Product/EditProduct.vue";
import ShowDetails from "../views/Product/ShowDetails.vue";
import ListProducts from "../views/Category/ListProducts.vue";
import Signup from '@/views/Signup.vue'
import Signin from '@/views/Signin.vue'
import Wishlist from '@/views/Product/Wishlist.vue'

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/category/show/:id',
    name: 'ListProducts',
    component: ListProducts
  },
  {
    path: '/admin/category/add',
    name: 'AddCategory',
    component: AddCategory
  },
  {
    path: '/admin/category',
    name: 'Category',
    component: Category
  },
  {
    path: '/admin/category/:id',
    name: 'EditCategory',
    component: EditCategory
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/admin/product',
    name: 'AdminProduct',
    component: Product
  },
  {
    path: '/admin/product/new',
    name: 'AddProduct',
    component: AddProduct
  },
  {
    path: '/admin/product/:id',
    name: 'EditProduct',
    component: EditProduct
  },

  {
    path: '/products/show/:id',
    name: 'ShowDetails',
    component: ShowDetails
  },

  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  
  {
    path: '/signin',
    name: 'Signin',
    component: Signin
  },

  {
    path: '/wishlist',
    name: 'Wishlist',
    component: Wishlist
  },

  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
