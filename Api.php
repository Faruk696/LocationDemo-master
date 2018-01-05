
<?php 
	
	//Constants for database connection
	define('DB_HOST','localhost');
	define('DB_USER','uhurte_faruk');
	define('DB_PASS','B#e0&PATet{6');
	define('DB_NAME','uhurte_faruk');

	//We will upload files to this folder
	//So one thing don't forget, also create a folder named uploads inside your project folder i.e. MyApi folder
	define('UPLOAD_PATH', 'uploads/');
	
	//connecting to database 
	$conn = new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME) or die('Unable to connect');
	

	//An array to display the response
	$response = array();

	//if the call is an api call 
	if(isset($_GET['apicall'])){
		
		//switching the api call 
		switch($_GET['apicall']){
			
			//if it is an upload call we will upload the image
			case 'uploadpic':
				
				//first confirming that we have the image and tags in the request parameter
				if(isset($_POST['latitude']) && isset($_POST['longitude']) && isset($_POST['image'])){
					
					//uploading file and storing it to database as well 
					try{
						$stmt = $conn->prepare("INSERT INTO data (latitude, longitude, image) VALUES (?,?,?)");
						$stmt->bind_param("sss", $_POST['latitude'],$_POST['longitude'], $_POST['image']);
						if($stmt->execute()){
							$response['error'] = false;
							$response['message'] = 'File uploaded successfully';
						}else{
							throw new Exception("Could not upload file");
						}
					}catch(Exception $e){
						$response['error'] = true;
						$response['message'] = 'Could not upload file';
					}
					
				}else{
					$response['error'] = true;
					$response['message'] = "Required params not available";
				}
			
			break;
			
			//in this call we will fetch all the data 
			case 'getpics':
		
				//getting server ip for building image url 
				$server_ip = gethostbyname(gethostname());
				
				//query to get data from database
				$stmt = $conn->prepare("SELECT id, latitude, longitude, image FROM data");
				$stmt->execute();
				$stmt->bind_result($id, $latitude, $longitude, $image);
				
				$data = array();

				//fetching all the data from database
				//and pushing it to array 
				while($stmt->fetch()){
					$temp = array();
					$temp['id'] = $id; 
					$temp['latitude'] = $latitude; 
					$temp['longitude'] = $longitude;
					$temp['image'] = $image;

					array_push($data, $temp);
				}
				
				//pushing the array in response 
				$response['error'] = false;
				$response['data'] = $data; 
			break; 
			
			default: 
				$response['error'] = true;
				$response['message'] = 'Invalid api call';
		}
		
	}else{
		header("HTTP/1.0 404 Not Found");
		echo "<h1>404 Not Found</h1>";
		echo "The page that you have requested could not be found.";
		exit();
	}
	
	//displaying the response in json 
	header('Content-Type: application/json');
	echo json_encode($response);