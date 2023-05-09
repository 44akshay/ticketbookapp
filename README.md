# Ticketbookapp
The project is created on React as frontend and Springboot as backend\
Spring boot is deployed on AWS Elastic Beanstalk and React is hosted on free platform called netlify\
Here is the Live link of working project [Live Link!](https://ticketbooklive.netlify.app) 
# Update
I have changed my DB hosting from AWS Rds to phpmysql free service, since aws free tier limit of 750 hrs will be crossing and so website will be working slow

# Database structure
![TicketDB](https://user-images.githubusercontent.com/32939560/236049910-42cfc00e-7332-4c0b-8eb1-3c99e8cd8654.png)

# My Approach
I have created an array of empty seats on each row, according to problem first priority is to assign seats in same row,
so in case if the seats are availabe the it will be assigned. Now in case the seats are not available in same row then 
second priority is to assign the nearest seats, so for this condition I have mapped this problem to min subarray sum and 
so the least row from the array will be assigned and in case condition is not satisfied then seats won't be booked and pop 
up will be shown to the user.
