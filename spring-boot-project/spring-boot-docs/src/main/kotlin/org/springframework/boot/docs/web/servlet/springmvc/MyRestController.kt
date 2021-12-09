/*
 * Copyright 2012-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.docs.web.servlet.springmvc

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class MyRestController(val userRepository: UserRepository, val customerRepository: CustomerRepository) {
	@GetMapping("/{user}")
	fun getUser(@PathVariable userId: Long): User {
		return userRepository.findById(userId).get()
	}

	@GetMapping("/{user}/customers")
	fun getUserCustomers(@PathVariable userId: Long): List<Customer> {
		return userRepository.findById(userId).map { user: User? ->
			customerRepository.findByUser(
				user
			)
		}.get()
	}

	@DeleteMapping("/{user}")
	fun deleteUser(@PathVariable userId: Long) {
		userRepository.deleteById(userId)
	}
}
