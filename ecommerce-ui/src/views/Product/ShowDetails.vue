<template>
    <div class="container p-4">
      <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-4 col-12">
          <img :src="product.imageURL" :alt="product.name" class="img-fluid" />
        </div>
        <div class="col-md-6 col-12 pt-3 pt-md-0">
          <h4>{{ product.name }}</h4>
          <h6 class="category font-italic">{{ category.categoryName }}</h6>
          <h6 class="font-weight-bold"> {{ product.price }} €</h6>
          <p>
            {{ product.description }}
          </p>
  
          <div class="d-flex flex-row justify-content-between">
            <div class="input-group col-md-3 col-4 p-0">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">Quantity</span>
              </div>
              <input class="form-control" type="number" v-bind:value="quantity" />
            </div>
  
            <div class="input-group col-md-3 col-4 p-0">
              <button
                type="button"
                id="add-to-cart-button"
                class="btn"
                @click="addToCart(this.id)"
              >
                Add to Cart
                <ion-icon name="cart-outline" v-pre></ion-icon>
              </button>
            </div>
          </div>
  
          <div class="features pt-3">
            <h5><strong>Features</strong></h5>
            <ul>
              <li>Lorem ipsum dolor sit amet consectetur adipisicing elit.</li>
              <li>Officia quas, officiis eius magni error magnam voluptatem</li>
              <li>nesciunt quod! Earum voluptatibus quaerat dolorem doloribus</li>
              <li>molestias ipsum ab, ipsa consectetur laboriosam soluta et</li>
              <li>ut doloremque dolore corrupti, architecto iusto beatae.</li>
            </ul>
          </div>
  
          <button
            id="wishlist-button"
            class="btn mr-3 p-2 py-0"
            :class="{'btn-blue': isAddedToWishlist == false, 'btn-red': isAddedToWishlist == true}"
            @click="addToWishListOrRemove(this.id)"
          >
            {{ wishlistString }}
          </button>
          <button
            id="show-cart-button"
            type="button"
            class="btn mr-3 p-2 py-0"
            @click="listCartItems()"
          >
            Show Cart
  
            <ion-icon name="cart-outline" v-pre></ion-icon>
          </button>
        </div>
        <div class="col-md-1"></div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import swal from 'sweetalert';
  export default {
    data() {
      return {
        product: {},
        category: {},
        id: null,
        token: null,
        isAddedToWishlist: false,
        wishlistString: "Add to wishlist",
        quantity: 1,
      };
    },
    props: ["baseURL", "products", "categories"],
    methods: {
        addToWishListOrRemove(productId) {
        if (!this.token) {
          swal({
            text: "Please sign in to add to wishlist",
            icon: "error",
          });
          return;
        }

        axios.get(`${this.baseURL}/wishlist/check`, {
          params: { id: productId, token: this.token }
        })
        .then(response => {
          if (response.data) {
            // If the item is in the wishlist, remove it
            axios.post(`${this.baseURL}/wishlist/remove?token=${this.token}`, {
              id: productId,
            })
            .then(response => {
              if (response.status === 200) {
                this.isAddedToWishlist = false;
                this.wishlistString = "Add to wishlist";
                swal({
                  text: "Item successfully removed from wishlist",
                  icon: "info",
                });
              }
            })
            .catch(error => {
              console.error("Error removing item from wishlist:", error);
              swal({
                text: "Unable to remove item from wishlist",
                icon: "error",
              });
            });
          } else {
            // If the item is not in the wishlist, add it
            axios.post(`${this.baseURL}/wishlist/add?token=${this.token}`, {
              id: productId,
            })
            .then(response => {
              if (response.status === 201) {
                this.isAddedToWishlist = true;
                this.wishlistString = "Remove from wishlist";
                swal({
                  text: "Item successfully added to wishlist",
                  icon: "success",
                });
              }
            })
            .catch(error => {
              console.error("Error adding item to wishlist:", error);
              swal({
                text: "Unable to add item to wishlist",
                icon: "error",
              });
            });
          }
        })
        .catch(error => {
          console.error("Error checking wishlist status:", error);
          swal({
            text: "Error checking wishlist status",
            icon: "error",
          });
        });
      },


      addToCart(productId) {
        if (!this.token) {
          swal({
            text: "Please sign in first",
            icon: "error",
          });
          return;
        }
        axios
          .post(`${this.baseURL}/cart/add?token=${this.token}`, {
            productId: productId,
            quantity: this.quantity,
          })
          .then(
            (response) => {
              if (response.status == 201) {
                swal({
                  text: "Product added to the cart",
                  icon: "success",
                  closeOnClickOutside: false,
                });
                // refresh nav bar
                this.$emit("fetchData");
              }
            },
            (error) => {
              console.log(error);
            }
          );
      },
  
      listCartItems() {
        axios.get(`${this.baseURL}/cart/?token=${this.token}`).then(
          (response) => {
            if (response.status === 200) {
              this.$router.push("/cart");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      },
    },
    mounted() {
      this.id = this.$route.params.id;
      this.product = this.products.find((product) => product.id == this.id);
      this.category = this.categories.find(
        (category) => category.id == this.product.categoryId
      );
      this.token = localStorage.getItem("token");

      if (this.token) {
      axios.get(`${this.baseURL}/wishlist/check`, {
        params: { id: this.id, token: this.token }
      })
      .then(response => {
        if (response.data) {
          this.isAddedToWishlist = true;
          this.wishlistString = "Remove from wishlist";
        } else {
          this.isAddedToWishlist = false;
          this.wishlistString = "Add to wishlist";
        }
      })
      .catch(error => {
        console.error("Error checking wishlist status:", error);
      });
    }
    },
  };
  </script>
  
  <style>
  .category {
    font-weight: 400;
  }
  
  /* Chrome, Safari, Edge, Opera */
  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
  
  /* Firefox */
  input[type="number"] {
    appearance: textfield;
    -moz-appearance: textfield;
  }
  
  #add-to-cart-button {
    background-color: #febd69;
  }
  
  #wishlist-button {
  }
  
  #show-cart-button {
    background-color: rgb(0, 0, 0);
    color: white;
  }

  .btn-blue {
  background-color: rgb(86, 178, 235);
  color: white;
}

.btn-red {
  background-color: rgb(235, 69, 95);
  color: white;
}
  </style>