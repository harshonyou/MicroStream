# Base URL for Video Service and Subscription Service
$videoServiceUrl = "http://localhost:8282"
$subscriptionServiceUrl = "http://localhost:8585"

# Function to post a video
Function Post-Video {
    param (
        [string]$userId,
        [string]$videoId,
        [string]$title,
        [string]$tags
    )

    $body = @{
        userId = $userId
        videoId = $videoId
        title = $title
        tags = @($tags -split ',')
    } | ConvertTo-Json

    Invoke-RestMethod -Method Post -Uri "$videoServiceUrl/api/v1/users/$userId/videos/" -Body $body -ContentType "application/json"
}

# Function to like a video
Function Like-Video {
    param (
        [string]$userId,
        [string]$videoId
    )

    Invoke-RestMethod -Method Post -Uri "$videoServiceUrl/api/v1/users/$userId/videos/$videoId/like" -ContentType "application/json"
}

# Function to watch a video
Function Watch-Video {
    param (
        [string]$userId,
        [string]$videoId
    )

    Invoke-RestMethod -Method Post -Uri "$videoServiceUrl/api/v1/users/$userId/videos/$videoId/watch" -ContentType "application/json"
}

# Function to subscribe to a tag
Function Subscribe-To-Tag {
    param (
        [string]$userId,
        [string]$tagName
    )

    Invoke-RestMethod -Method Post -Uri "$subscriptionServiceUrl/api/v1/users/$userId/tags/$tagName/subscribe" -ContentType "application/json"
}

# Simulate posting videos
Post-Video "1" "1fcd04f0-9f1a-11ee-995e-fffdb2b698b7" "Nature Documentary" "Nature"
Post-Video "2" "2fcd04f0-9f1a-11ee-995e-fffdb2b698b7" "Cooking Tutorial" "Cooking"
Post-Video "3" "3fcd04f0-9f1a-11ee-995e-fffdb2b698b7" "Tech Review" "Technology"
Post-Video "4" "4fcd04f0-9f1a-11ee-995e-fffdb2b698b7" "Travel Vlog" "Travel"
Post-Video "5" "5fcd04f0-9f1a-11ee-995e-fffdb2b698b7" "Himalaya Tour" "Travel,Nature"


# Simulate liking and watching videos
Like-Video "3" "1fcd04f0-9f1a-11ee-995e-fffdb2b698b7"
Watch-Video "3" "1fcd04f0-9f1a-11ee-995e-fffdb2b698b7"
Like-Video "1" "3fcd04f0-9f1a-11ee-995e-fffdb2b698b7"
Like-Video "2" "4fcd04f0-9f1a-11ee-995e-fffdb2b698b7"
Watch-Video "2" "4fcd04f0-9f1a-11ee-995e-fffdb2b698b7"
Like-Video "4" "2fcd04f0-9f1a-11ee-995e-fffdb2b698b7"

# Simulate subscribing to tags
Subscribe-To-Tag "2" "Nature"
Subscribe-To-Tag "3" "Technology"
Subscribe-To-Tag "1" "Travel"
Subscribe-To-Tag "4" "Cooking"
