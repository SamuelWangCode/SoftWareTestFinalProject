<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div id="mypreloader">
<loading></loading>
</div>


<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<div class="main-header">
	<div ></div>
	<div class="content-bg-wrap bg-badges">
		<div class="row">
			<div class="col col-lg-8 m-auto col-md-8 col-sm-12 col-12">
				<div class="main-header-content">
					<h1>Your Account Dashboard</h1>
					<p>Welcome to your account dashboard! Here you’ll find everything you need to change your profile!!</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ... end Main Header Account -->


<!-- Your Account Personal Information -->

<div class="container">
	<div class="row">
		<div class="col col-xl-9 order-xl-2 col-lg-9 order-lg-2 col-md-12 order-md-1 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">Search User</h6>
					<a href="#" class="more"><svg class="olymp-three-dots-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
					<div class="w-search">
						<div class="form-group with-button">
							<input class="form-control" type="text" v-model="username" placeholder="Search the forums...">
							<button @click="SearchUser">
								<i class="fa fa-search-plus fa-2x" aria-hidden="true"></i>
							</button>
						</div>
					</div>				
                </div>

				
				<!-- Notification List Frien Requests -->
				
				<ul class="notification-list friend-requests">
					<li v-for="(myfollower, index) in User" :key="index">
						<div class="author-thumb">
							<img :src=(temp+myfollower.icon) alt="author">
						</div>
						<div class="notification-event">
							<a @click="moveToOpage(myfollower.uid)" class="h6 notification-friend">{{myfollower.nickName}}</a>
						</div>
					</li>		
                    <div v-show="!User.length" class="text" style="text-align:center">
                         ---No User---
                    </div>	
				</ul>
				
				<!-- ... end Notification List Frien Requests -->
			</div>

		</div>



		<div class="col col-xl-3 order-xl-1 col-lg-3 order-lg-1 col-md-12 order-md-2 col-sm-12 col-12 responsive-display-none">
			<div class="ui-block">

				<!-- Your Profile  -->
				
				<div class="your-profile">
					<div class="ui-block-title ui-block-title-small">
						<h6 class="title">Your PROFILE</h6>
					</div>
				
					<div id="accordion" role="tablist" aria-multiselectable="true">
						<div class="card">
							<div class="card-header" role="tab" id="headingOne">
								<h6 class="mb-0">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
										Profile Settings
										<svg class="olymp-dropdown-arrow-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-dropdown-arrow-icon"></use></svg>
									</a>
								</h6>
							</div>
				
							<div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
								<ul class="your-profile-menu">
									<li>
										<a href="http://localhost:8080/personalinfo">Personal Information</a>
									</li>
									<li>
										<a href="http://localhost:8080/myfollows">My Follows</a>
									</li>
								</ul>
							</div>
						</div>
					</div>

					<div class="ui-block-title">
						<a href="http://localhost:8080/searchuser" class="h6 title">Search User</a>
					</div>

					<div class="ui-block-title ui-block-title-small">
						<h6 class="title">FAVOURITE Guide</h6>
					</div>
					<div class="ui-block-title">
						<a href="http://localhost:8080/postguide" class="h6 title">Create Guide</a>
					</div>
					<div class="ui-block-title">
						<a href="http://localhost:8080/myguide" class="h6 title">My Guide</a>
					</div>
				</div>
				
				<!-- ... end Your Profile  -->

			</div>
		</div>
	</div>
</div>

<!-- ... end Your Account Personal Information -->



<a class="back-to-top" href="#">
	<i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i>
</a>


</div>


</template>>

<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'
setTimeout(function () {
			$('#mypreloader').fadeOut(600);
		}, 1500)
export default {
  name: 'searchUser',
  data () {
    return {
		username: '',
		User: [],
		temp: '/static/img/userIcon/',
    }
  },
  methods: {
      SearchUser(){
		this.$axios
        .get('/manageInformation-producer/api/searchUserByName/' + this.username)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.User = successResponse.data.data
            this.$dlg.toast("success", {messageType: 'success', closeTime: 5})
          }
          if (successResponse.data.code === 400) {
            this.$dlg.toast(successResponse.data.msg, {messageType: 'error', closeTime: 5})
          }
        })
        .catch(failResponse => {})
	  },
	   moveToOpage(oid){
        this.$router.push({name:'OPersonalInfo',query:{Oid:oid}});
      }
  },
  mounted() {

  },
  created(){
  },
  components:{
	  headline,
	  loading
  },
}
</script>