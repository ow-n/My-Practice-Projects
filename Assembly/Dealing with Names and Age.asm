# Dealing with Names & Age

.globl main

.data
name:			.asciiz		"What is your name? "
age:			.asciiz		"What is your age? "
hello:			.asciiz		"Hello, "
future_age1:	.asciiz		"You will be "
future_age2:	.asciiz		" years old in four years"
				.align 2	# power of 2 = 4 bytes, which is a word boundary
answer: .space 51			# will hold up to 50 characters, so the name must be 50 characters or less
alength: .word 50

.text
main:
	# Asks Information
	li $v0,4 			# Service Print String
	la $a0, name 		# Loads name
	syscall
	li $v0,8 			# Service Read String
	la $a0,answer 		# put address of answer string in $a0
	lw $a1,52($a0)		# put length of string in $a1 - fixed form lw, rx, Label
	syscall

	li $v0,4 			# Service Print String
	la $a0, age 		# Loads age
	syscall
	li $v0,5 			# Service Read integer
	syscall
	addi $t2, $v0, 4	# Add 4 to $v0 and store the result in $t2

	# Response
	li $v0,4			# Service Print String
	la $a0, hello		# Loads hello
	syscall
	la $a0, answer		# Loads answer (input name)
	syscall
	la $a0, future_age1	# Loads "You will be"
	syscall
	li $v0,1			# Service Print Integer
	la $a0, ($t2)		# Loads future age
	syscall
	li $v0,4			# Service Print String
	la $a0, future_age2	# Loads "years old in four years"
	syscall
	
	# Exit
	li $v0, 10			# Service Exit
	syscall


# Output
# What is your name? Harry Potter
# What is your age? 11
# Hello, Harry Potter
# You will be 15 years old in four years