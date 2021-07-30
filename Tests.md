### <p align="center">Tests.md</font>

**Things need to know:**

1, In every situation, it is supposed to just have one window on the screen

2, Enter the separator(“/br/”) in creating/editing account name, password and postings (including title, context and author name)

### Test 1: User Creates an Account


**1.1** Create the account successfully

Steps:

1.  User launches application.
    
2.  User selects “Create an Account” and clicks on “OK”.
<br>**![](https://lh3.googleusercontent.com/h4iE5VU7yffHzRZsVK2SpxktzvE_Ye737xpE4-LjUK6xPU4ra78sGD0LObZaLrFFUrRNjd7MdD-raFPeXwnuM8ZAQckZOg2KvzrKnBXyz4cv6-R2gYx4MEtZTGAU2OjPtd9BcaBm)**
1.  User enters the new account name via keyboard and clicks on “OK”.
    
2.  User enters the new password and clicks “OK”.
    

(In the example, we take “newUser” as the account name and “newPassword” as the password)

Expected result:

The window shows the message “Added!” and returns to the initial window after clicking on “OK”, which means that it is ready for signing in.
  
**![](https://lh5.googleusercontent.com/z9AVbBsJ-9azfUBurWegunCeJOWibIlZ7_JbSeKFhsf0wDlFf4SXycTv3fqQAyG1CoIVWDmzk1-s3RvHThjyVd9b8WeqxPYVStmlE8m5URiNniJfDq9lCt-S-YXY2Xh6sSuPHPHb)**
<br>The user can also see his/her account is added in *Accouts.txt*.
**![](https://lh6.googleusercontent.com/t2GJMSPLv_vo6f241XzSwUzPuWg_BMvku3_WFOQH0VcHb7PrdPkb004cdYi4TQd5rfD9WtTsM7JYG_6PCCOvLOfGpDY1RR8CtFX0d9HkEIM8hqNu744T4p8ISywsP2UVhWnK78yJ)**
<br>Test Status: Passed.

**1.2** When the account name already existed - unsuccessfully


Steps:

1.  User runs “Server.java” and then runs “User.java”.
    
2.  User selects “Create an Account” and clicks on “OK”.
    
3.  User enters the new account name via keyboard and clicks on “OK”.
    
4.  User enters the new password and clicks “OK”.
    

(In this situation, we take “newUser” and “random” as the example”)

  

Expected result:

The window shows the error message that “This account name already existed, try again with a different name!” and returns to the initial window after clicking on “OK”.
<br>**![](https://lh4.googleusercontent.com/VX6z9sQZTSC9O24KqwEaN27QnW39JvcNdzFTBfY6nhhCadfCKnkFSrs2OJ30G87cVUSD5GOwybatfinOL2RpnaGG-7QRMaNbe2qwiMYik6GfSMwLXHPYX_bhXGNvqwjQSo1fCRJH)**
<br>Test Status: Passed.

  

**1.3** Either the account name or the password is null(“”) - unsuccessfully

  

Steps:

1.  User runs “Server.java” and then runs “User.java”.
    
2.  User selects “Create an Account” and clicks on “OK”.
    
3.  User enters the new account name via keyboard and clicks on “OK”.
    
4.  User enters the new password and clicks “OK”.
    

  

Tips:

Outputs are the same in these three situations:

1. The account name is null and the password is not, such as “” and “pw”.

2. The account name is not null and the password is null, such as “ac” and “”.

3.  Both the account name and the password are null: “” and “”.

  

Expected result:

The window shows the error message “You need to type something!” and returns to the initial window.
<br>**![](https://lh6.googleusercontent.com/P1ZD3ir8Q1mywlJDOoPFDKu9aGAPFtgy9Bw7mZPUlKQEJI-DHw1hoRP_vmZ4l8myMj6NqQJdwgcOtLXPxXkjpb4WuhoKeA34gAHO2ONuMWDLBC-77dlQn_QKGHfQZX3TFJttpiZ4)**
<br>Test Status: Passed.

  

### Test 2: User log in

  

**2.1** Logged in successfully

Steps:

1.  User launches application.
    
2.  User selects the “Log in” Option and clicks on “OK”.
<br>**![](https://lh4.googleusercontent.com/6BR59LCjDr0hV8EcT5OapJGQQR9moeR6Xe5wRrl_o3nu5HTxd1o6HwxW2leuLXyhXuDa4V3CBmtz90uANIm0eqOy4WvM8_53ARrxpBjz1iBNwf8gDt9iZ98nbUuQCZNfszoKs12S)**
<br>1.  User selects the account name textbox and enters via keyboard.
    
2.  User selects the password textbox and enters via keyboard.
    
3.  User clicks on the "Log in" button.
    

(We used the registered account “newUser” and “newPassword”)

Expected result: Application verifies the user's username and password and loads the confirmation message, after clicking on “OK”, it will load the welcome message. Click on “OK” again to access the main menu of Posting Application.
<br>**![](https://lh6.googleusercontent.com/px-9Ih0iohqw_CYyZ9hWldU-1b17-rSgOh-hy1P-TomrbCrn0TU6xD8F5GfBp5otz9zJt9RXDwqSOLrbTgjJiTLQmK-US3np6FzUeke3lBWt11Ui3HDWVg-Sh9vA5VAlPUAyegGR)**
<br>**![](https://lh3.googleusercontent.com/26k4sYIyxAtevGZaIeNXQKBXE57SIHtGLUuvi0_onQdUV53UgvUGhtkkeO-Vfqdb3aG9QfbvOMj8PPpg0z01vqHYomCL98u3GXOT0S3U5E0zHAF5KCSDVqOhmHOsHbzwSpEDV2K7)**
<br>Test Status: Passed.

  

**2.2** The inputted account name and password cannot be found - unsuccessfully

Steps:

1.  User launches application.
    
2.  User selects the “Log in” Option and clicks on “OK”.
    
3.  User selects the account name textbox and enters via keyboard.
    
4.  User selects the password textbox and enters via keyboard.
    
5.  User clicks on the "Log in" button.
    

(We used “abc” and “xyz” in this situation)

Expected result:
<br>**![](https://lh3.googleusercontent.com/UGh2ZXarMLLjndF_hw5TX1oYb8VxUjIo3yZ4kASSoL8azy-3w-l2oYQuMp-Tfa5qO4EYzapfg77sseyLwv1ycDndVhptXoy5x0zjqBrYHsqpRB_woks8d04lz7MrSE3L-wdWavOR)**
<br>The window shows the error message and goes back to the initial window after clicking on “OK”.

Test Status: Passed.

### Test 3: Someone is using this account

 Steps:

1.  After logging in successfully in User.java, run User2.java.
    
2.  Select “Log in”, then enter the same account name and password as what in User.java.
    
3.  Press the “OK” button.
    

(We use “newUser” and “newPassword” in this situation)

Expected result:

The error message is shown.
<br>**![](https://lh4.googleusercontent.com/41e_wO8BTEq2CkCAquuRwACpVMVVK-b-ka47b69pcakOzbnmi_oe7dIKfgny8jVvJ3QSX5dnMymctSGE009vPRwA9jJjOuLOjJWN5DM2skO9gDJlPt8_Pi30XcjjJhoS-GfP4mdV)**
<br>After clicking on “OK”, go back to the initial window.

Test Status: Passed.

### Test 4: See the list of posts (There is no posting in this application)

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “See the list of posts” and click on “OK”.
    

Expected result:

Show the message that “No one has written any posts!”.

<br>![](https://lh6.googleusercontent.com/4WGOyirETfseUWBHHDhSppUlucfLIeRXVNpfvMGCUkoWf1DMubwshkivTo_GXaCa3YhGM9u05b8yLkS0Dq217W-YSlz5ANzNLqaGXEF89-Kf1ywiTkxIFuS9y3-d-5P_0djeOhPm)

<br>After clicking on “OK”. it will return to the main menu.

Test Status: Passed.

### Test 5: Create a new post - unsuccessfully

At least one of the three features (title, context and author name) is null(“”)

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “Create a new post” and click on “OK”.
    
3.  Enter title in the selected textbox and press “OK”.
    
4.  Enter context in the selected textbox and press “OK”.
    
5.  Enter author name in the selected textbox and press “OK”.
    

（In this situation, we entered “”, “?’ and “xyz”).

Expected result:

Show the error message “Failed to Create Post (check if at least one of the inputs is empty)”

  

Click “OK” and return to the main menu.

Test Status: Passed.

### Test 6: Edit your posts (You have not written any posts)

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “edit your posts” and click on “OK”.
    

Expected result:

Show the error message as below.

<br>![](https://lh3.googleusercontent.com/8F85AjXTuc6Z5X1Go5H4K4oI45adZ8rIwwVrX8bIaVqcRcGcDFQ_OGHYJAfclhNYEfX7tqQAcX37zNTZlmXqsvffltS4tWJWz_5NQ029NZqjDgVMmDgSK5eDU1FTg4eOrXcrOBqA)

Click “OK” to return to the main menu.
Test Status: Passed.

### Test 7: Delete one of your posts (You have not written any posts)

 Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “delete one of your posts” and click on “OK”.
    

Expected result:

Show the error message as below.

<br>![](https://lh4.googleusercontent.com/SPwXoraGjLDO4IMt3s5YBgW8uj3x_3VLhucI8qU_d_nekwZmHOsBhHqJ4DFd488FaOSpWxNNLVL0RITnDaC1rizOzlAaIamoL2NSYoMd4INgDa9CDVb8euQoDw11ubwDkxs6sY2i)

<br>Click “OK” to return to the main menu.

Test Status: Passed.

### Test 8: Create a post - successfully

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “Create a new post” and click on “OK”.
    
3.  Enter title in the selected textbox and press “OK”.
    
4.  Enter context in the selected textbox and press “OK”.
    
5.  Enter author name in the selected textbox and press “OK”.
    
(In this situation, we entered “?”, “confused” and “xyz”)

Expected result:

Show the confirmation message as below.

![](https://lh5.googleusercontent.com/O2DUWl4zkJIMaN1l61WkcwpYIzYdQhKZmkAJVU_zHv5YEKndG4ET8CoZD_KXSZZHg68a2fwERZI45oler4rV9OI0j2ussiMKgfOw_H7SgrpWHZHWg7kUeWn7E6DSgRGPbsUucckq)

<br>The user can also see their posting added in Posts.txt.

<br>![](https://lh6.googleusercontent.com/ztZvx4nh2NAlMnuKHl_fIKr8iy-oAppNkOW9GfGUpz4LQFtvTIf_iJ1TXgoAAyvk8VvB9xKGiLgfkaPkWwR3t9L9rP-V2TX3dpXFn8tz4UsaN-2vrs8Iblms2qqjZWeubrwdyMVY)

<br>After clicking on “OK”, it will return to the main menu.

Test Status: Passed.

### Test 9: Edit your post - invalid selection

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “edit your posts” and click on “OK”.
    
3.  Enter “p” in the textField and press “OK”.
    

Expected result:

Show the error message as below.

<br>![](https://lh4.googleusercontent.com/OI__viyQS7HdOq67xB8zIAlrfWHDvUs1m3SmCSv6pjMx8yqscqCS23QFD9Y99uw4tnqoAwsge4c2znhSFPyNgEieKju0MoBWFV317sNXBOMMxw5gSL35fK1y2_Yacwj-DI3a6EJh)

<br>Test Status: Passed.

### Test 9: Edit your post - the input is null

Steps:

1.  Select “Post” and click on “OK” in the main menu.
    
2.  Select “edit your posts” and click on “OK”.
    
3.  Enter “1” in the textField and press “OK”.
    
4.  Select “Edit Title”, “Edit Author name” or “Edit Context”, then press “OK”.
    
5.  Do not enter anything and click on “OK” directly.
    

Expected result:

Show the error message as below.

<br>![](https://lh5.googleusercontent.com/ltVEYgJg0zjI3a-_qnkIullli0CxFZzIURPWUFh0gV03kJep7CKejaCEXjG1epqfKRPkzQe4RCqtATvwJ3gBuu7rSxl1_8ho22gYYFa8EfZQXiOmrnawHQJHwHl7NYSATRAS0aKI)

<br>Test Status: Passed.

  

### Test 10: See the list of posts - successfully

Steps:

1.  Run User2.java, follow the instructions of Test 1 and Test 2, create an account with the account name “ac2” and password “pw2” and log in.
    
2.  Follow steps in Test 8, create a posting by entering “t” as title, “c” as context and “a” as author name.
    
3.  Select “Post” and click on “OK” in the main menu.
    
4.  Select “See the list of posts” and click on “OK”.
    

Expected result:

Show all users’ postings in the JTextArea, and postings are sorted from latest to oldest.

![](https://lh4.googleusercontent.com/zfAd2mMtgLHYvJEF-pgy7UGxisSOz68LnAi8nmwj8NNntstyPTCOSdb_S1vkOnareT_8dCqHUxu85vRZyeDkW6YvBWfpkgbZ5dFJTjImX7j9BM-tluu_iM6kqqzPkdrknWXs3RUe)

<br>Test Status: Passed.

### Test 11: Edit your post - successfully

Steps:

1.  In the account for User2, select “Post” and click on “OK” in the main menu.
    
2.  Select “edit your posts” and click on “OK”.
    
3.  Enter “1” in the textField and press “OK”.
    
4.  Select “Edit Title”, then press “OK”.
    
5.  Enter “title2”, press “OK”.
    

(We take “Edit Title” as an example here. “Edit Author name” or “Edit Context” are similar)

Expected result:

Show the confirmation message as below. 
<br>![](https://lh6.googleusercontent.com/1WdND8WjTafAW1TBVBKgUruygj8r1FtGWKpwFClLuGF_tH3UQglD66CsWMJr9sqqpmE42i42NQyTcBG9_tVtwQC3gMpTqLG_GZqkM3gOJD4FF2IvBzTzBYSBgsL0sLkjzmLM2jy7)

<br>(“Edit Author name” will show a message “Author Name Edited!” and “Edit Context” will show a message “Context Edited!”)

Now, the user can see the edited posting in Posts.txt.

![](https://lh6.googleusercontent.com/qYYUXAHiZcQ3OWtjKGcgG-BQQ_6d6QzJ1yf3sfk8H7wPqZaPJc6f646KWK2Jn7BZ6RQuDqCQ0yTss0uKmB5ZqGjNWllzjciCS9wyn7rSXrEapq8DP26WZu-EgR6uWXnMGgoNlJvd)

Test Status: Passed.

### Test 12: Delete one of your postings - successfully

Steps:

1.  In the account for User2, select “Post” and click on “OK” in the main menu.
    
2.  Select “delete one of your posts” and click on “OK”.
    
3.  Enter “1” in the textField and press “OK”.
    

  

Expected result:

Show the confirmation message as below.

<br>![](https://lh6.googleusercontent.com/HnoEwbAiWlQeQDoX9BV8Hi135VIBGHonORYsjkL2pZPiA5V_d4SVeXPiVoHSFczLYry-RiU5FnFzd89Cy8ZDFUCuwFiC-0Z16AtEYJwb1h6XLarC6maS4505RMSZSo53jE5fjrw4)

The User can also find that the posting is removed in Posts.txt.

<br>![](https://lh5.googleusercontent.com/kvAxT9Uw4Xb8unaGg5Ma5oVjwUZHLdIqbAs5LS4efA1HBtwcwPVkLj8JTxuYy5Jv77ENu0qW5rQhDKv7gUF304WKyyQkHl9I1ecXJb7ePoJlOOJ4l6aXjbh5DRlcFOCGzBHn_849)

Test Status: Passed.

  

### Test 13: Account Setting (Edit Account Name) - the input is null

Steps:

  

1.  After logging in with User.java, select “Account Setting”
    
2.  Select “Edit” and then select “Account name”
    
3.  Do not enter anything and click on “OK” directly.
    

  

Expected result:

  

Show the error message as below.

  

<br>![](https://lh4.googleusercontent.com/nETi1-ei0zEOJ5gJhdFq9BakurvaEdB7M4c3Pe23FtEtupe85xVomgt8tOD9Fc0Wx-WA3HqJCyZvN9a7O-xWrttuLOvwyouYgvqXEzNoXNXGS1rjhodqvoW4FatB7qcfFmMeAXv5)

Test Status: Passed.

### Test 14: Account Setting (Edit password) - the input is null

Steps:

  

1.  After logging in with User.java, select “Account Setting”
    
2.  Select “Edit” and then select “Password”
    
3.  Do not enter anything and click on “OK” directly.
    

  

Expected result:

  

Show the error message as below.

<br>![](https://lh4.googleusercontent.com/nETi1-ei0zEOJ5gJhdFq9BakurvaEdB7M4c3Pe23FtEtupe85xVomgt8tOD9Fc0Wx-WA3HqJCyZvN9a7O-xWrttuLOvwyouYgvqXEzNoXNXGS1rjhodqvoW4FatB7qcfFmMeAXv5)

Test Status: Passed.

  

### Test 15: Account Setting - new Account name/password is same as current one

Steps:

  

1.  After logging in with User.java, select “Account Setting”
    
2.  Select “Edit” and then select “Account name”
    
3.  Enter current account name.
    

Expected result:

  

Show the error message as below.

![](https://lh3.googleusercontent.com/Zs4pswSXHssZA6dGlRX5wcgV2Qlwa5CTgoym5-5Crh8dbTPBjYlYxhU1RhNiGJoSDbXm1E9ildHEwOu25DMssC_qvaRAM1tmYMnlTWsDybdw4_rqiMQ-yt2Rtj60rDSgusGp2jdA)

(For editing the password, the situation is similar)

Test Status: Passed.

  
  

### Test 16: Account Setting (Delete Account) - successfully

Steps:

  
1.  After logging in with User.java, select “Account Setting”
    
2.  Select “Delete” and then Select “Yes”
    

  

Expected result:

  

Show the confirmation message as below.

  

![](https://lh5.googleusercontent.com/8fpOp4m5gvmSoF4CZs0sm3f7ZQQ8E4gnyLKncnFTwq3LHOSz2pxig8yI4FQfBxBTwL-jSxBRlnvB6mYUmNVqZ3azbU5p40ev2-WCuq0LDlrfRePLpW8FU8pWTLNov8AfhWIaVktc)

  

Test Status: Passed.

### Test 17: Account Setting (Delete Account) - successfully

Steps:

  

1.  After logging in with User.java, select “Account Setting”
    
2.  Select “Delete” and then Select “Yes”
    

  

Expected result:

  

Show the confirmation message as below.

![](https://lh5.googleusercontent.com/8fpOp4m5gvmSoF4CZs0sm3f7ZQQ8E4gnyLKncnFTwq3LHOSz2pxig8yI4FQfBxBTwL-jSxBRlnvB6mYUmNVqZ3azbU5p40ev2-WCuq0LDlrfRePLpW8FU8pWTLNov8AfhWIaVktc)

Test Status: Passed.

### Test 18: Log out - successfully

Steps:

  

After logging in with the Account name "User1", select “Log out”

  

Expected result:

  

Show the confirmation message as below.

![](https://lh4.googleusercontent.com/z4AlJ7vp5RIgHwwCsVg3NKxgQfP6GhmwBByIS9PMgU96Dgj6Q2bP4kQ2MRKyLVGyClzckcOmU2ZktO_Gl9739B8zzJ5J9RjPmU3_JsgrtpcKKPxLABxTirVAVoD32gJkQuJdCscw)

Test Status: Passed.
 

### Test 19: Real-Time updates

Steps:

  

1.  Users launch the application through User.java and User2.java
    
2.  Create a random post in User.java
    
3.  Select “See the list of posts” from User2.java to make sure a post created in User.java is updated.
    

  

Expected result:

(The post created in User.java should be on the list)

![](https://lh3.googleusercontent.com/VGpj-S6r-LyKvIUgKLRQNAzuNnyoygGET169KOTwmzVtl_VhONrGmjmyx9WLy8YpxMtfHAtJ_tF6Zf6hyXFFgbQx8HA7ggFxRjcLK-weWAluYjJBC9e_mFVU0pZNLOdRkKhjLOw8)

Test Status: Passed.

  

### Test 20: Deleting posts when delete account

Steps:

1.  Users launch the application through User.java and User2.java
    
2.  Create three random post in User.java
    
3.  Delete the account from User.java
    
4.  Select “See the list of posts” from User2.java to make sure three posts created in User.java are deleted.
    

  

Expected result:

![](https://lh3.googleusercontent.com/S3c-rTZropn-LE3ezulSVXCA05IIqQ8KV1JUnREhCevS-vUEmzm_hcmGavJEBRKfaGAJJh_TSCDWjYPHu4pXzfV1T3v0bLCXZcuFfL-KqvHLEjw6q3bp8mI_zBTOV5vDU4ZQjipm)

(Three posts created by user in User.java)

![](https://lh3.googleusercontent.com/kKfYV9EVxQUvNhQl3UiImyhGWxhnIk92_R0GLYeqB7MxsugLhETIg7wVobydCwDQTv49ArHLDrE6lg321_Emv3ZgIWCZevDH5_17YCcsmxL_xRqgNTKjv7axyPNA1y5q84BCqt6c)

(After deleting the account, in User2.java, there is no post remaining)

Test Status: Passed.
