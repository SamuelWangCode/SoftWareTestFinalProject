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
					<h6 class="title">Personal Information</h6>
				</div>
				<div class="ui-block-content">

					
					<!-- Personal Information Form  -->
					
					<form>
						<div class="row">
					
							<div class="col col-lg-12 col-md-12 col-sm-12 col-15">
								<div class="form-group label-floating">
									<label class="control-label"> </label>
									<input class="form-control" v-model="UserAccount.name" placeholder="enter your nick name..">
								</div>
					
								<div class="form-group label-floating">
									<label class="control-label">Your Account</label>
									<input class="form-control"  type="email" :value=UserAccount.account disabled="true">
								</div>
					
							</div>
					
							<div class="col col-lg-6 col-md-6 col-sm-12 col-12">
					
								<div class="form-group label-floating is-select">
									<label class="control-label">Your Gender</label>
									<select v-model="UserAccount.gender">
										<option value="0">Unknown</option>
										<option value="1">Male</option>
										<option value="2">Female</option>
									</select>
								</div>												
							</div>
							<div class="col col-lg-6 col-md-6 col-sm-12 col-12">
				
								<div class="form-group label-floating is-select">
									<label class="control-label">Prefer</label>
									<select v-model="UserAccount.prefer">
										<option value="0">Null</option>
										<option value="1">Attack</option>
										<option value="2">Defend</option>
										<option value="3">Support</option>
									</select>
								</div>
							</div>
							<div class="col col-lg-12 col-md-12 col-sm-12 col-15" style="text-align:center">
								<button class="btn btn-primary btn-lg" @click="updateUserInfo">Save all Changes</button>
							</div>
					
						</div>
					</form>
					
					<!-- ... end Personal Information Form  -->
				</div>
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
  name: 'personalInfor',
  data () {
    return {
	 	UserAccount:{
		account: sessionStorage.getItem("UuAccount") , 
		name:sessionStorage.getItem("UuName") , 
	  	icon:'/static/img/userIcon/'+sessionStorage.getItem("UuImage") , 
		uid : sessionStorage.getItem("UuID"),
		gender : sessionStorage.getItem("UuGender"),
		prefer : sessionStorage.getItem("UuPrefer")},
		
    }
  },
  methods: {
	  init(){
		  this.$axios
		.get('/manageInformation-producer/api/myInformation/' + sessionStorage.getItem("UuID"))
        .then(successResponse => {
			this.responseResult = JSON.stringify(successResponse.data)
        	if (successResponse.data.code === 200) {
				this.UserAccount.name=successResponse.data.data.nickName
				this.UserAccount.icon='/static/img/userIcon/'+successResponse.data.data.icon
				this.UserAccount.gender=successResponse.data.data.gender
				this.UserAccount.prefer=successResponse.data.data.prefer
        	}
        	if (successResponse.data.code === 400) {
				alert(successResponse.data.message)
			}
        })
        .catch(failResponse => {})
	  },
	  updateUserInfo () {
		  if(this.UserAccount.name == ''){
              alert("Nick name can not be empty")
          }else{
              this.$axios
          		.post('/manageInformation-producer/api/setting', {
            		nickName:this.UserAccount.name , 
					gender : this.UserAccount.gender,
					prefer : this.UserAccount.prefer,
					uid : this.UserAccount.uid
          		})
          		.then(successResponse => {
            	this.responseResult = JSON.stringify(successResponse.data)
            	if (successResponse.data.code === 200) {
					sessionStorage.setItem("UuName",this.UserAccount.name)
					sessionStorage.setItem("UuGender",this.UserAccount.gender)
					sessionStorage.setItem("UuPrefer",this.UserAccount.prefer)
					alert("修改成功")					
			   		location.reload()
            	}
           		if (successResponse.data.code === 400) {
             	 	alert(successResponse.data.message)
            	}
          		})
          		.catch(failResponse => {})
            } 
	},
  },
  mounted() {
	  this.init()
  },
  created(){
  },
  components:{
	  headline,
	  loading
  },
}
</script>

