# BlissBites

## Introduction
BlissBites is an online dessert ordering service for a wide variety of desserts including cakes, cupcakes, brownies, muffins, cookies, and candies.

Users are able to select dessert items to cart and place their orders.

Admins are able to add/update the dessert products users select.

## Storyboard

![Wireframe for BlissBites](StoryBoard.png)

## Functional Requirements
#As a buyer, I want a cart, so that I can store the items I want to buy in one place as I shop.
<b>Given</b> a user is browsing the menu
<b>When</b> they click on a food item to “add to cart”
<b>Then</b> the item should be added to the shopping cart

#As a store owner, I want payment processing, so that I can see past transactions and update my inventory
(should be split into three different ideally for each then)
<b>Given</b> a user browsed the menu and picked their desired items
<b>When</b> the user finalizes their purchase
<b>Then</b> the system should securely process the payment, log it, and update inventory

<b>Given</b> users have been buying a particular item
<b>When</b> there is none of the particular item left in stock
<b>Then</b> the menu should display there is none of that item left in stock, and prevent anyone from adding it to their cart

#As a buyer, I want a filter, so that I can filter the menu for items I’m looking for.
<b>Given</b> a user is browsing the menu
<b>When</b> the user applies the filter for a specific preference
<b>Then</b> the displayed menu should include items according to the filter

## Class Diagram
![UML Diagram for BlissBites](UML.png)

## JSON Schema
![JSON Schema](JSONSchema.txt)

## Team Memebers and Roles
Github Admin/Developer - Vishvak Chintalapalli\
Product Owner/Developer - Dakota Smith\
Developer - Elliot Phillips\
Developer - Ravi Patel\
Developer - Aj White

## Standup
Team meets every Thursday at 4:00pm in CECH Library
