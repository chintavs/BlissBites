# BlissBites

## Introduction
BlissBites is an online dessert ordering service for a wide variety of desserts including cakes, cupcakes, brownies, muffins, cookies, and candies.

Users are able to select dessert items to cart and place their orders.

Admins are able to add/update the dessert products users select.

## Storyboard

![Wireframe for BlissBites](StoryBoard.png)

## Functional Requirements
# As a buyer, I want a cart, so that I can store the items I want to buy in one place as I shop.
**Given** a user is browsing the menu
**When** they click on a food item to “add to cart”
**Then** the item should be added to the shopping cart

# As a store owner, I want payment processing, so that I can see past transactions and update my inventory
(should be split into three different ideally for each then)
**Given** a user browsed the menu and picked their desired items
**When** the user finalizes their purchase
**Then** the system should securely process the payment, log it, and update inventory

**Given** users have been buying a particular item
**When** there is none of the particular item left in stock
**Then** the menu should display there is none of that item left in stock, and prevent anyone from adding it to their cart

# As a buyer, I want a filter, so that I can filter the menu for items I’m looking for.
**Given** a user is browsing the menu
**When** the user applies the filter for a specific preference
**Then** the displayed menu should include items according to the filter

## Class Diagram
![UML Diagram for BlissBites](UML.png)

## JSON Schema
![JSON Schema](JSONSchema.txt)

## Team Memebers and Roles
Github Admin/Developer - Vishvak Chintalapalli\
Product Owner/Scrum Master/Developer - Dakota Smith\
Developer - Elliot Phillips\
Developer - Ravi Patel\
Developer - Aj White

## Standup
Team meets every Thursday at 4:00pm in CECH Library
